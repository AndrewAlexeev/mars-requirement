package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.BulletListVariable;
import pl.jsolve.templ4docx.variable.ImageVariable;
import pl.jsolve.templ4docx.variable.TableVariable;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variable;
import pl.jsolve.templ4docx.variable.Variables;
import ru.mokb.mars.requirements.database.DepartmentJpaRepository;
import ru.mokb.mars.requirements.database.DepartmentSubsystemJpaRepository;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.ResultJpaRepository;
import ru.mokb.mars.requirements.database.SubsystemJpaRepository;
import ru.mokb.mars.requirements.database.model.Department;
import ru.mokb.mars.requirements.database.model.DepartmentSubsystem;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.database.model.Result;
import ru.mokb.mars.requirements.database.model.Subsystem;
import ru.mokb.mars.requirements.rest.controllers.ActController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActControllerImpl implements ActController {
	private static final String ATTACHMENT = "attachment;filename=";
	private static final MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

	private final PIMJpaRepository pimJpaRepository;
	private final SubsystemJpaRepository subsystemJpaRepository;
	private final DepartmentSubsystemJpaRepository departmentSubsystemJpaRepository;
	private final DepartmentJpaRepository departmentJpaRepository;
	private final ResultJpaRepository resultJpaRepository;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM");

	@Value("${templatePath}")
	Resource resource;
//	@Value("classpath:ru.mokb.mars.template/g")
//	Resource imageRes;

	@Override
	public ResponseEntity<byte[]> generateAct(Double pimId, Integer subsystemId) throws IOException {

		Subsystem subsystem = subsystemJpaRepository.findById(subsystemId).orElseThrow();

		DepartmentSubsystem departmentSubsystem = departmentSubsystemJpaRepository.findById(subsystem.getName()).orElseThrow();
		Department department = departmentJpaRepository.findById(departmentSubsystem.getDepartment()).orElseThrow();

		PIM pim = pimJpaRepository.findById(pimId).orElseThrow();

		Docx docx = new Docx(resource.getInputStream());
		docx.setVariablePattern(new VariablePattern("${", "}"));

		Variables var = new Variables();

		TableVariable tableVariable = new TableVariable();

		List<Variable> modeColumnVariables = new ArrayList<>();
		List<Variable> resultColumnVariables = new ArrayList<>();
		List<Variable> pointsColumnVariables = new ArrayList<>();
		pim.getModes().forEach(mode -> {

			List<Variable> points = new ArrayList<>();
			List<Variable> results = new ArrayList<>();


			mode.getTechnicalTaskPoints()
					.stream()
					.filter(point -> {
						Integer pointSubsystemId = point.getTechnicalTaskSystem().getSubsystem().getId();
						return subsystemId.equals(pointSubsystemId);
					})
					.forEach(point -> {
						points.add(new TextVariable("${points}", point.getName()));
						Integer pointId = point.getId();
						Double modeId = mode.getId();
						Integer id = resultJpaRepository.findModePointIdByModeAndPoint(modeId, pointId);
						Result result = resultJpaRepository.findByModePointId(id).orElseGet(() -> {
							Result result1 = new Result();
							result1.setBody("");
							return result1;
						});
						results.add(new TextVariable("${result}", result.getBody()));
					});


			if (!CollectionUtils.isEmpty(points)) {
				modeColumnVariables.add(new TextVariable("${mode}", mode.getSimulatedMode()));
				resultColumnVariables.add(new BulletListVariable("${result}", results));

				points.add(new TextVariable("${points}", ""));
				pointsColumnVariables.add(new BulletListVariable("${points}", points));
			}

		});

		tableVariable.addVariable(modeColumnVariables);
		tableVariable.addVariable(resultColumnVariables);
		tableVariable.addVariable(pointsColumnVariables);

		var.addTableVariable(tableVariable);

		var.addTextVariable(new TextVariable("${objectName}", pim.getLAObject().getId()));
		var.addTextVariable(new TextVariable("${pimNumber}", pim.getId().toString()));
		var.addTextVariable(new TextVariable("${subsystemName}", subsystem.getName()));
		List<String> departmentChiefPart = List.of(department.getChief().split("\r\n"));
		var.addTextVariable(new TextVariable("${departmentChief}", departmentChiefPart.get(0)));
		var.addTextVariable(new TextVariable("${departmentChiefFIO}", departmentChiefPart.get(1)));
		var.addTextVariable(new TextVariable("${departmentNumber}", department.getId()));


		LocalDateTime localDateTime = LocalDateTime.now();
		var.addTextVariable(new TextVariable("${date}", dateTimeFormatter.format(localDateTime)));

//		List<ImageVariable> images = new ArrayList();
//
//		File logo = imageRes.getFile();
//
//
//		ImageVariable imageVariable = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
//		ImageVariable imageVariable2 = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
//		ImageVariable imageVariable3 = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
//		images.add(imageVariable);
//		images.add(imageVariable2);
//		images.add(imageVariable3);

//		BulletListVariable bulletListVariable = new BulletListVariable("${photo}",images);
//
//		var.addBulletListVariable(bulletListVariable);
		docx.fillTemplate(var);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.save(outputStream);
		//docx.save(templatePath + "out.docx");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT + "act.docx")
				.contentType(mediaType)
				.body(outputStream.toByteArray());
	}


}
