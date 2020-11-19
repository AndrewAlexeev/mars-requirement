package ru.mokb.mars.requirements.rest.controllers.impl;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.XDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.velocity.internal.VelocityTemplateEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
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
import ru.mokb.mars.requirements.model.Result;
import ru.mokb.mars.requirements.rest.controllers.ActController2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequiredArgsConstructor
public class ActControllerImpl2 implements ActController2 {

	private final PIMJpaRepository pimJpaRepository;

	@Value("${templatePath}")
	private String templatePath;

	@Value("classpath:ru.mokb.mars.template/act3.docx")
	Resource resource;
	@Value("classpath:ru.mokb.mars.template/g")
	Resource imageRes;
	@Override
	public byte[] addMode(Integer pimId) throws IOException {
		try {
			// 1) Load Docx file by filling Velocity template engine and cache
			// it to the registry
			InputStream in = resource.getInputStream();
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
					in, TemplateEngineKind.Velocity);

			// 2) Create fields metadata to manage lazy loop (#forech velocity)
			// for table row.
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("results.Mode");
			metadata.addFieldAsList("results.Result");
			metadata.addFieldAsList("results.TaskPoint");
			report.setFieldsMetadata(metadata);
			// 3) Create context Java model
			Properties properties = new Properties();
			properties.setProperty("resource.loader", "class");
			properties.setProperty(
					"class.resource.loader.class",               "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//			ITemplateEngine templateEngine = new VelocityTemplateEngine(properties);
//
//			report.setTemplateEngine(templateEngine);
			IContext context = report.createContext();

			List<Result> results = new ArrayList<Result>();
			results.add(new Result("ZERR", "Angelo",
					"angelo.zerr@gmail.com"));
			results.add(new Result("Leclercq", "Pascal",
					"pascal.leclercq@gmail.com"));
			context.put("results", results);

			// 4) Generate report by merging Java model with the Docx
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			report.process(context, out);
			return out.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
		return null;
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
