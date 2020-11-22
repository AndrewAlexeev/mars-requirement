package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.Subsystem;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskSystem;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskSystemResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskSystemsResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicalTaskSystems2FetchTechnicalTaskSystemsConverter {

	public static FetchTechnicalTaskSystemsResponse convert(List<TechnicalTaskSystem> technicalTaskSystems) {
		List<FetchTechnicalTaskSystemResponse> fetchTechnicalTaskSystemResponses = technicalTaskSystems.stream()
				.map(TechnicalTaskSystems2FetchTechnicalTaskSystemsConverter::convert)
				.collect(Collectors.toList());
		FetchTechnicalTaskSystemsResponse fetchTechnicalTaskSystemsResponse = new FetchTechnicalTaskSystemsResponse();
		fetchTechnicalTaskSystemsResponse.setSystems(fetchTechnicalTaskSystemResponses);
		return fetchTechnicalTaskSystemsResponse;

	}

	public static FetchTechnicalTaskSystemResponse convert(TechnicalTaskSystem technicalTaskSystem) {
		FetchTechnicalTaskSystemResponse fetchTechnicalTaskSystemResponse = new FetchTechnicalTaskSystemResponse();
		fetchTechnicalTaskSystemResponse.setId(technicalTaskSystem.getId());
		Subsystem subsystem = technicalTaskSystem.getSubsystem();
		fetchTechnicalTaskSystemResponse.setSystem(subsystem.getName());
		return fetchTechnicalTaskSystemResponse;
	}

}
