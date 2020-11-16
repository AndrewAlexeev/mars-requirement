package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TechnicalTaskFetchFileRequest {
	@NotBlank
	private Integer technicalTaskId;
}
