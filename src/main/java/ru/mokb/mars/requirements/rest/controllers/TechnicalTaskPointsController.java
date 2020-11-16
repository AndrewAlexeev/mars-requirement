package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.requests.TechnicalTaskPointAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointsResponse;

@RequestMapping(
		path = "/"
)
public interface TechnicalTaskPointsController {

	@ResponseBody
	@PostMapping("/technical-task-point")
	Integer addTechnicalTaskPoint(@RequestBody TechnicalTaskPointAddRequest technicalTaskPointAddRequest);

	@ResponseBody
	@GetMapping("/technical-task-points")
	FetchTechnicalTaskPointsResponse fetchTechnicalTasks();


}




