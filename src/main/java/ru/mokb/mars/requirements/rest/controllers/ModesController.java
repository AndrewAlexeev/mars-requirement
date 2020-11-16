package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.requests.ModeAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;

@RequestMapping(
		path = "/",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
)
public interface ModesController {

	@ResponseBody
	@PostMapping("/mode")
	Integer addMode(@RequestBody ModeAddRequest modeAddRequest);

	@ResponseBody
	@GetMapping("/modes")
	FetchObjectsResponse fetchModes();
}