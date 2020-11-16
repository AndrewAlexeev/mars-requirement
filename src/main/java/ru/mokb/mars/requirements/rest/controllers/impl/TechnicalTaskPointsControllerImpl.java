package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.TechnicalTaskJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskPointJpaRepository;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.rest.controllers.TechnicalTaskPointsController;
import ru.mokb.mars.requirements.rest.requests.TechnicalTaskPointAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskPointsResponse;

@RestController
@RequiredArgsConstructor
public class TechnicalTaskPointsControllerImpl implements TechnicalTaskPointsController {
	private final TechnicalTaskJpaRepository technicalTaskJpaRepository;
	private final TechnicalTaskPointJpaRepository technicalTaskPointJpaRepository;

	@Override
	public Integer addTechnicalTaskPoint(TechnicalTaskPointAddRequest technicalTaskPointAddRequest) {
		TechnicalTask technicalTask = technicalTaskJpaRepository.findById(technicalTaskPointAddRequest.getTechnicalTaskId()).orElseThrow();
		TechnicalTaskPoint technicalTaskPoint = new TechnicalTaskPoint();
		technicalTaskPoint.setName(technicalTaskPointAddRequest.getName());
		technicalTaskPoint.setTechnicalTask(technicalTask);
		TechnicalTaskPoint savedTechnicalTaskPoint = technicalTaskPointJpaRepository.save(technicalTaskPoint);
		return savedTechnicalTaskPoint.getId();
	}

	@Override
	public FetchTechnicalTaskPointsResponse fetchTechnicalTasks() {
		return null;
	}
}
