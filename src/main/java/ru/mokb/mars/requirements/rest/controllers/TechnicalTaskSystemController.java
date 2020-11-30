package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskSystemsResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;

@RequestMapping(
		path = "/api"
)
public interface TechnicalTaskSystemController {

	@ResponseBody
	@PostMapping("/technicalTask/system")
	Integer addTechnicalTaskSystem(@RequestParam("technicalTaskId") Integer technicalTaskId, @RequestParam("systemId") Integer systemId);

	@ResponseBody
	@GetMapping("/technicalTask/system")
	FetchTechnicalTaskSystemsResponse fetchTechnicalTaskSystem(@RequestParam("technicalTaskId") Integer technicalTaskId);
}