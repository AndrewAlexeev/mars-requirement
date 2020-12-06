package ru.mokb.mars.requirements.database.model;

import lombok.Data;

@Data
public class BoundingRect {
	private Integer x1;
	private Integer x2;
	private Integer y1;
	private Integer y2;
	private Integer width;
	private Integer height;
}
