package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import pl.jsolve.sweetener.io.Resources;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.BulletListVariable;
import pl.jsolve.templ4docx.variable.ImageType;
import pl.jsolve.templ4docx.variable.ImageVariable;
import pl.jsolve.templ4docx.variable.TableVariable;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variable;
import pl.jsolve.templ4docx.variable.Variables;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.ActController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActControllerImpl implements ActController {

	private final PIMJpaRepository pimJpaRepository;

	@Value("${templatePath}")
	private String templatePath;

	@Value("classpath:ru.mokb.mars.template/act2.docx")
	Resource resource;
	@Value("classpath:ru.mokb.mars.template/g")
	Resource imageRes;
	@Override
	public byte[] addMode(Integer pimId) throws IOException {
		PIM pim = pimJpaRepository.findById(pimId).orElseThrow();
		pim.getModes();

		Docx docx = new Docx(resource.getInputStream());
		docx.setVariablePattern(new VariablePattern("${", "}"));

		Variables var = new Variables();

		TableVariable tableVariable = new TableVariable();

		List<Variable> modeColumnVariables = new ArrayList<Variable>();
		List<Variable> resultColumnVariables = new ArrayList<Variable>();
		List<Variable> pointsColumnVariables = new ArrayList<Variable>();

		modeColumnVariables.add(new TextVariable("${mode}", "режим 1"));
		modeColumnVariables.add(new TextVariable("${mode}", "режим 2"));
		modeColumnVariables.add(new TextVariable("${mode}", "режим 3"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 1"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 2"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 3"));


		List<Variable> points = new ArrayList<Variable>();
		points.add(new TextVariable("${points}", "Пункт 1"));
		points.add(new TextVariable("${points}", "Пункт 2"));
		points.add(new TextVariable("${points}", "Пункт 3"));

		pointsColumnVariables.add(new BulletListVariable("${points}", points));
		pointsColumnVariables.add(new BulletListVariable("${points}", points));
		pointsColumnVariables.add(new BulletListVariable("${points}", points));


		tableVariable.addVariable(modeColumnVariables);
		tableVariable.addVariable(resultColumnVariables);
		tableVariable.addVariable(pointsColumnVariables);

		var.addTableVariable(tableVariable);

		List<ImageVariable> images = new ArrayList();

		File logo = imageRes.getFile();


		ImageVariable imageVariable = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
		ImageVariable imageVariable2 = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
		ImageVariable imageVariable3 = new ImageVariable("${photo}" , logo, ImageType.PNG, 75, 75);
		images.add(imageVariable);
		images.add(imageVariable2);
		images.add(imageVariable3);

		BulletListVariable bulletListVariable = new BulletListVariable("${photo}",images);

		var.addBulletListVariable(bulletListVariable);
		docx.fillTemplate(var);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.save(outputStream);
		//docx.save(templatePath + "out.docx");
		return outputStream.toByteArray();
	}

	@Override
	public byte[] addMode2() {
		PIM pim = pimJpaRepository.findById(100).orElseThrow();
		pim.getModes();

		Docx docx = new Docx(templatePath + "act.docx");
		docx.setVariablePattern(new VariablePattern("${", "}"));

		Variables var = new Variables();

		TableVariable tableVariable = new TableVariable();

		List<Variable> modeColumnVariables = new ArrayList<Variable>();
		List<Variable> resultColumnVariables = new ArrayList<Variable>();
		List<Variable> pointsColumnVariables = new ArrayList<Variable>();

		modeColumnVariables.add(new TextVariable("${mode}", "режим 1"));
		modeColumnVariables.add(new TextVariable("${mode}", "режим 2"));
		modeColumnVariables.add(new TextVariable("${mode}", "режим 3"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 1"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 2"));
		resultColumnVariables.add(new TextVariable("${result}", "результат режима 3"));


		List<Variable> points = new ArrayList<Variable>();
		points.add(new TextVariable("${points}", "Пункт 1"));
		points.add(new TextVariable("${points}", "Пункт 2"));
		points.add(new TextVariable("${points}", "Пункт 3"));

		pointsColumnVariables.add(new BulletListVariable("${points}", points));
		pointsColumnVariables.add(new BulletListVariable("${points}", points));
		pointsColumnVariables.add(new BulletListVariable("${points}", points));


		tableVariable.addVariable(modeColumnVariables);
		tableVariable.addVariable(resultColumnVariables);
		tableVariable.addVariable(pointsColumnVariables);

		var.addTableVariable(tableVariable);
		docx.fillTemplate(var);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.save(outputStream);
		docx.save(templatePath + "out.docx");
		return outputStream.toByteArray();
	}
}
