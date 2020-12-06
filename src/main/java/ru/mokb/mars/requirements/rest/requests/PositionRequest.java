package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

@Data
public class PositionRequest {
	private Integer pageNumber;
	private BoundingRectRequest boundingRect;
}
