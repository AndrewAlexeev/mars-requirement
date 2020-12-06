package ru.mokb.mars.requirements.database.model;

import lombok.Data;

@Data
public class Position {
	private BoundingRect boundingRect;
	private Integer pageNumber;
}
