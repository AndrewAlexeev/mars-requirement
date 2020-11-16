package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FetchTechnicalTasksResponse {
	private List<FetchTechnicalTaskResponse> technicalTasks = new ArrayList<>();
}
