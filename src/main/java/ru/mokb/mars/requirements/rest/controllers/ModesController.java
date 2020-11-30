package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.requests.FetchModesRequest;
import ru.mokb.mars.requirements.rest.responses.FetchModesResponse;

@RequestMapping(
		path = "/api",
		produces = MediaType.APPLICATION_JSON_VALUE)
public interface ModesController {

	@ResponseBody
	@GetMapping("/modes")
	FetchModesResponse fetchModes(@RequestParam("pimId") Double pimId);
}
