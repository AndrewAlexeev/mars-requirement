package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.SubsystemJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskSystemJpaRepository;
import ru.mokb.mars.requirements.database.model.Subsystem;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskSystem;
import ru.mokb.mars.requirements.rest.controllers.TechnicalTaskSystemController;
import ru.mokb.mars.requirements.rest.responses.FetchTechnicalTaskSystemsResponse;
import ru.mokb.mars.requirements.rest.utils.TechnicalTaskSystems2FetchTechnicalTaskSystemsConverter;

@RestController
@RequiredArgsConstructor
public class TechnicalTaskSystemControllerImpl implements TechnicalTaskSystemController {
	private final TechnicalTaskSystemJpaRepository technicalTaskSystemJpaRepository;
	private final SubsystemJpaRepository subsystemJpaRepository;
	private final TechnicalTaskJpaRepository technicalTaskJpaRepository;

	@Override
	public Integer addTechnicalTaskSystem(Integer technicalTaskId, Integer systemId) {
		TechnicalTaskSystem technicalTaskSystem = new TechnicalTaskSystem();
		Subsystem subsystem = subsystemJpaRepository.findById(systemId).orElseThrow();
		TechnicalTask technicalTask = technicalTaskJpaRepository.findById(technicalTaskId).orElseThrow();
		technicalTaskSystem.setSubsystem(subsystem);
		technicalTaskSystem.setTechnicalTask(technicalTask);
		TechnicalTaskSystem savedTechnicalTaskSystem = technicalTaskSystemJpaRepository.save(technicalTaskSystem);
		return savedTechnicalTaskSystem.getId();
	}

	@Override
	public FetchTechnicalTaskSystemsResponse fetchTechnicalTaskSystem(Integer technicalTaskId) {
		TechnicalTask technicalTask = technicalTaskJpaRepository.findById(technicalTaskId).orElseThrow();
		return TechnicalTaskSystems2FetchTechnicalTaskSystemsConverter.convert(technicalTask.getTechnicalTaskSystems());
	}
}
