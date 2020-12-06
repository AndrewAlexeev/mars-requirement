package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

@Data
public class BoundingRectResponse {
	private Integer x1;
	private Integer x2;
	private Integer y1;
	private Integer y2;
	private Integer width;
	private Integer height;
}
