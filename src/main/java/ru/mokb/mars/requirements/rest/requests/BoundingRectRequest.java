package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

@Data
public class BoundingRectRequest {
	private Integer x1;
	private Integer x2;
	private Integer y1;
	private Integer y2;
	private Integer width;
	private Integer height;
}
