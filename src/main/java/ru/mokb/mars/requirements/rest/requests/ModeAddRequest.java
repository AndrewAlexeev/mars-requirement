package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ModeAddRequest {
	@NotBlank
	private String name;
	private List<Integer> pimIds;
}
