package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mokb.mars.requirements.rest.requests.TechnicalTaskFetchFileRequest;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;

import java.io.IOException;

@RequestMapping(
		path = "/"
)
public interface TechnicalTasksController {

	@ResponseBody
	@PostMapping("/technical-task")
	Integer addTechnicalTask(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("objectId") Integer objectId);

	@ResponseBody
	@GetMapping("/technical-tasks")
	FetchTechnicalTasksResponse fetchTechnicalTasks();

	@RequestMapping("/technical-task/download")
	public ResponseEntity<InputStreamResource> downloadFile(@RequestBody TechnicalTaskFetchFileRequest technicalTaskFetchFileRequest) throws IOException;

}




