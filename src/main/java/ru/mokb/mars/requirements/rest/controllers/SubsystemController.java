package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mokb.mars.requirements.rest.responses.FetchSubsystemsResponse;

@RequestMapping(
		path = "/"
)
public interface SubsystemController {

	@ResponseBody
	@GetMapping("/subsystems")
	FetchSubsystemsResponse fetchSubsystems(@RequestParam("objectId") String objectId);

}
