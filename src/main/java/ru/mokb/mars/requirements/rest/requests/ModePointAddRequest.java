package ru.mokb.mars.requirements.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ModePointAddRequest {
	@NotNull
	private Integer modeId;
	@NotNull
	private List<Integer> pointIds;
}
