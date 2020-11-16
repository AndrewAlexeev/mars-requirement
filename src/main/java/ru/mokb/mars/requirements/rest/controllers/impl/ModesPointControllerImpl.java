package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ModeJpaRepository;
import ru.mokb.mars.requirements.database.TechnicalTaskPointJpaRepository;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.rest.controllers.ModesPointController;
import ru.mokb.mars.requirements.rest.requests.ModePointAddRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModesPointControllerImpl implements ModesPointController {
	private final ModeJpaRepository modeJpaRepository;
	private final TechnicalTaskPointJpaRepository technicalTaskPointJpaRepository;

	@Override
	public Integer addModePoint(ModePointAddRequest modePointAddRequest) {
		Mode mode = modeJpaRepository.findById(modePointAddRequest.getModeId()).orElseThrow();
		List<TechnicalTaskPoint> technicalTaskPointList = technicalTaskPointJpaRepository.findAllById(modePointAddRequest.getPointIds());
		mode.getTechnicalTaskPoints().addAll(technicalTaskPointList);
		modeJpaRepository.save(mode);
		return null;
	}
}
