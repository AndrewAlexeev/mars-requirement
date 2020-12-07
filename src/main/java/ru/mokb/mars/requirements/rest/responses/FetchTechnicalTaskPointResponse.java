package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

@Data
public class FetchTechnicalTaskPointResponse {
	private Integer id;
	private String name;
	private String description;
	private PositionResponse position;
}
