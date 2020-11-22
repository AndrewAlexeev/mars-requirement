package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ModeJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskPointJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskSystemJpaRepository;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.database.model.TechnicalTaskSystem;
import ru.mokb.mars.requirements.rest.controllers.TechnicalTaskPointsController;
import ru.mokb.mars.requirements.rest.requests.TechnicalTaskPointAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointResponse;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointsResponse;
import ru.mokb.mars.requirements.rest.utils.TechnicalTaskPoints2FetchTechnicalTaskPointsConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TechnicalTaskPointsControllerImpl implements TechnicalTaskPointsController {
	private final TechnicalTaskPointJpaRepository technicalTaskPointJpaRepository;
	private final ModeJpaRepository modeJpaRepository;
	private final TechnicalTaskSystemJpaRepository technicalTaskSystemJpaRepository;


	@Override
	public Integer addTechnicalTaskPoint(TechnicalTaskPointAddRequest technicalTaskPointAddRequest) {
		TechnicalTaskSystem technicalTaskSystem = technicalTaskSystemJpaRepository.findById(technicalTaskPointAddRequest.getTechnicalTaskSystemId()).orElseThrow();
		TechnicalTaskPoint technicalTaskPoint = new TechnicalTaskPoint();
		technicalTaskPoint.setName(technicalTaskPointAddRequest.getName());
		technicalTaskPoint.setTechnicalTaskSystem(technicalTaskSystem);
		List<Mode> modes = modeJpaRepository.findAllById(technicalTaskPointAddRequest.getModes());

		TechnicalTaskPoint savedTechnicalTaskPoint = technicalTaskPointJpaRepository.save(technicalTaskPoint);

		modes.forEach(mode -> mode.getTechnicalTaskPoints().add(savedTechnicalTaskPoint));
		modeJpaRepository.saveAll(modes);

		return savedTechnicalTaskPoint.getId();
	}

	@Override
	public FetchTechnicalTaskPointsResponse fetchTechnicalTaskPoints(Integer technicalTaskId) {
		TechnicalTaskSystem technicalTaskSystem = technicalTaskSystemJpaRepository.findById(technicalTaskId).orElseThrow();

		List<TechnicalTaskPoint> technicalTaskPoints = technicalTaskPointJpaRepository.findAllByTechnicalTaskSystem(technicalTaskSystem);
		List<FetchTechnicalTaskPointResponse> fetchTechnicalTaskPointsResponses = technicalTaskPoints.stream()
				.map(TechnicalTaskPoints2FetchTechnicalTaskPointsConverter::convert)
				.collect(Collectors.toList());

		FetchTechnicalTaskPointsResponse fetchTechnicalTaskPointsResponse = new FetchTechnicalTaskPointsResponse();
		fetchTechnicalTaskPointsResponse.setPoints(fetchTechnicalTaskPointsResponses);
		return fetchTechnicalTaskPointsResponse;
	}
}
