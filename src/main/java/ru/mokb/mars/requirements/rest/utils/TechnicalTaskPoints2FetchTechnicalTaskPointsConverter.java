package ru.mokb.mars.requirements.rest.utils;

import liquibase.pro.packaged.B;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.BoundingRect;
import ru.mokb.mars.requirements.database.model.Position;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.rest.responses.BoundingRectResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointsResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTasksResponse;
import ru.mokb.mars.requirements.rest.responses.PositionResponse;

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

		fetchTechnicalTaskPointResponse.setPosition(convertPosition(technicalTaskPoint.getPosition()));
		return fetchTechnicalTaskPointResponse;
	}

	private static PositionResponse convertPosition(Position position) {
		PositionResponse positionResponse = new PositionResponse();
		positionResponse.setPageNumber(position.getPageNumber());
		BoundingRect boundingRect = position.getBoundingRect();
		BoundingRectResponse boundingRectResponse = new BoundingRectResponse();
		boundingRectResponse.setHeight(boundingRect.getHeight());
		boundingRectResponse.setWidth(boundingRect.getWidth());
		boundingRectResponse.setX1(boundingRect.getX1());
		boundingRectResponse.setX2(boundingRect.getX2());
		boundingRectResponse.setY1(boundingRect.getY1());
		boundingRectResponse.setY2(boundingRect.getY2());
		positionResponse.setBoundingRect(boundingRectResponse);
		return positionResponse;
	}

}
