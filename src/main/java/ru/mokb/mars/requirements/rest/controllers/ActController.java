package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@RequestMapping(
		path = "/api"
)
public interface ActController {

	@ResponseBody
	@GetMapping("/act")
	ResponseEntity<byte[]> generateAct(@RequestParam("pimId") Double pimId, @RequestParam("subsystemId") Integer subsystemId) throws IOException;

}
