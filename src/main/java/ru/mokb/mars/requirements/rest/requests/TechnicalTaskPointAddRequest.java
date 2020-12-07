package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TechnicalTaskPointAddRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	private Integer technicalTaskSystemId;
	@NotEmpty
	private List<Double> modes;

	private PositionRequest position;
}
