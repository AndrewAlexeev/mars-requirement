package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mokb.mars.requirements.database.ObjectJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskJpaRepository;
import ru.mokb.mars.requirements.database.model.LAObject;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.rest.controllers.TechnicalTasksController;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;
import ru.mokb.mars.requirements.rest.utils.TechnicalTasks2FetchTechnicalTasksConverter;
import ru.mokb.mars.requirements.services.StorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TechnicalTasksControllerImpl implements TechnicalTasksController {
	private final TechnicalTaskJpaRepository technicalTaskJpaRepository;
	private final ObjectJpaRepository objectJpaRepository;
	private final StorageService storageService;

	private static final String ATTACHMENT = "attachment;filename=";
	//TODO на данный момент хардкод на пдф
	private static final MediaType mediaType = MediaType.APPLICATION_PDF;

	@Value("${saveFilePath}")
	private String savePath;

	@Override
	public Integer addTechnicalTask(MultipartFile file, String name, String objectId) {

		TechnicalTask technicalTask = new TechnicalTask();
		technicalTask.setName(name);
		technicalTask.setPath(savePath);
		LAObject LAObject = objectJpaRepository.findById(objectId).orElseThrow();
		technicalTask.setLAObject(LAObject);
		TechnicalTask savedTechnicalTask = technicalTaskJpaRepository.save(technicalTask);
		storageService.store(file, savePath + savedTechnicalTask.getId() + ".pdf");

		return savedTechnicalTask.getId();
	}

	@Override
	public FetchTechnicalTasksResponse fetchTechnicalTasks(String objectId) {
		LAObject laObject = objectJpaRepository.findById(objectId).orElseThrow();
		List<FetchTechnicalTaskResponse> fetchTechnicalTaskResponseList = laObject.getTechnicalTaskList().stream()
				.map(TechnicalTasks2FetchTechnicalTasksConverter::convert)
				.collect(Collectors.toList());

		FetchTechnicalTasksResponse fetchTechnicalTasksResponse = new FetchTechnicalTasksResponse();
		fetchTechnicalTasksResponse.setTechnicalTasks(fetchTechnicalTaskResponseList);
		return fetchTechnicalTasksResponse;
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadFile(
			Integer technicalTaskId) throws IOException {
		TechnicalTask technicalTask = technicalTaskJpaRepository.findById(technicalTaskId).orElseThrow();


		File file = new File(technicalTask.getPath() + technicalTask.getId() + ".pdf");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT + file.getName())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(resource);
	}

}
