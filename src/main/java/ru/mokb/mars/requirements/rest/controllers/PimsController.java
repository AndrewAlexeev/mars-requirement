package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.responses.FetchPimsResponse;

@RequestMapping(
		path = "/",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
)
public interface PimsController {

	@ResponseBody
	@GetMapping("/pims")
	FetchPimsResponse fetchPIMs(@RequestParam("objectId") String objectId);
}
