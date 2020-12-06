package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

@Data
public class PositionResponse {
	private Integer pageNumber;
	private BoundingRectResponse boundingRect;
}
