package ru.mokb.mars.requirements.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@RequestMapping(
		path = "/"
)
public interface ActController {

	@ResponseBody
	@PostMapping("/act")
	byte[] addMode(@RequestBody Integer pimId) throws IOException;

	@ResponseBody
	@GetMapping("/acte")
	public byte[] addMode2();

}
