package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

import java.util.List;

@Data
public class FetchTechnicalTaskPointsResponse {
	private List<FetchTechnicalTaskPointResponse> points;
}
