package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;

@RequestMapping(
		path = "/api",
		produces = MediaType.APPLICATION_JSON_VALUE
)
public interface ObjectsController {

	@ResponseBody
	@GetMapping("/objects")
	FetchObjectsResponse fetchObjects();
}




