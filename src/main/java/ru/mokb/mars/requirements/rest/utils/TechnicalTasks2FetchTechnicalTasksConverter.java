package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicalTasks2FetchTechnicalTasksConverter {

	public static FetchTechnicalTasksResponse convert(List<TechnicalTask> technicalTasks) {
		List<FetchTechnicalTaskResponse> fetchObjectResponses = technicalTasks.stream()
				.map(TechnicalTasks2FetchTechnicalTasksConverter::convert)
				.collect(Collectors.toList());
		FetchTechnicalTasksResponse fetchObjectsResponse = new FetchTechnicalTasksResponse();
		fetchObjectsResponse.setTechnicalTasks(fetchObjectResponses);
		return fetchObjectsResponse;

	}

	public static FetchTechnicalTaskResponse convert(TechnicalTask technicalTask) {
		FetchTechnicalTaskResponse fetchTechnicalTaskResponse = new FetchTechnicalTaskResponse();
		fetchTechnicalTaskResponse.setId(technicalTask.getId());
		fetchTechnicalTaskResponse.setName(technicalTask.getName());
		return fetchTechnicalTaskResponse;
	}

}
