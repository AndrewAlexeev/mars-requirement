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
import ru.mokb.mars.requirements.database.model.Object;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.rest.controllers.TechnicalTasksController;
import ru.mokb.mars.requirements.rest.requests.TechnicalTaskFetchFileRequest;
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

	@Value("${saveFilePath}")
	private String savePath;

	@Override
	public Integer addTechnicalTask(MultipartFile file, String name, Integer objectId) {


		TechnicalTask technicalTask = new TechnicalTask();
		technicalTask.setName(name);
		technicalTask.setPath(savePath);
		Object object = objectJpaRepository.findById(objectId).orElseThrow();
		technicalTask.setObject(object);
		TechnicalTask savedTechnicalTask = technicalTaskJpaRepository.save(technicalTask);
		storageService.store(file, savePath + savedTechnicalTask.getId());

		return savedTechnicalTask.getId();
	}

	@Override
	public FetchTechnicalTasksResponse fetchTechnicalTasks() {

		List<FetchTechnicalTaskResponse> fetchTechnicalTaskResponseList = technicalTaskJpaRepository.findAll().stream()
				.map(TechnicalTasks2FetchTechnicalTasksConverter::convert)
				.collect(Collectors.toList());

		FetchTechnicalTasksResponse fetchTechnicalTasksResponse = new FetchTechnicalTasksResponse();
		fetchTechnicalTasksResponse.setTechnicalTasks(fetchTechnicalTaskResponseList);
		return fetchTechnicalTasksResponse;
	}

	public ResponseEntity<InputStreamResource> downloadFile(
			TechnicalTaskFetchFileRequest technicalTaskFetchFileRequest) throws IOException {
		TechnicalTask technicalTask = technicalTaskJpaRepository.findById(technicalTaskFetchFileRequest.getTechnicalTaskId()).orElseThrow();

		//TODO подумать насчет расширений
		MediaType mediaType = MediaType.IMAGE_PNG;
		File file = new File(technicalTask.getPath() + technicalTask.getId());
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT + file.getName())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(resource);
	}
}
