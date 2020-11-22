package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointsResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicalTaskPoints2FetchTechnicalTaskPointsConverter {

	public static FetchTechnicalTaskPointsResponse convert(List<TechnicalTaskPoint> technicalTaskPoints) {
		List<FetchTechnicalTaskPointResponse> fetchTechnicalTaskPointResponses = technicalTaskPoints.stream()
				.map(TechnicalTaskPoints2FetchTechnicalTaskPointsConverter::convert)
				.collect(Collectors.toList());
		FetchTechnicalTaskPointsResponse fetchTechnicalTaskPointsResponse = new FetchTechnicalTaskPointsResponse();
		fetchTechnicalTaskPointsResponse.setPoints(fetchTechnicalTaskPointResponses);
		return fetchTechnicalTaskPointsResponse;

	}

	public static FetchTechnicalTaskPointResponse convert(TechnicalTaskPoint technicalTaskPoint) {
		FetchTechnicalTaskPointResponse fetchTechnicalTaskPointResponse = new FetchTechnicalTaskPointResponse();
		fetchTechnicalTaskPointResponse.setId(technicalTaskPoint.getId());
		fetchTechnicalTaskPointResponse.setName(technicalTaskPoint.getName());
		return fetchTechnicalTaskPointResponse;
	}

}
