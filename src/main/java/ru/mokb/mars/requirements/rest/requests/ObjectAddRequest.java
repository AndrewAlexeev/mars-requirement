package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ObjectAddRequest {
	@NotBlank
	private String name;
}
