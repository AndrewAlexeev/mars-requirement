package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.ActController;
import ru.mokb.mars.requirements.rest.requests.ModeAddRequest;

@RestController
@RequiredArgsConstructor
public class ActControllerImpl implements ActController {

	private final PIMJpaRepository pimJpaRepository;

	@Override
	public Integer addMode(Integer pimId) {
		PIM pim = pimJpaRepository.findById(pimId).orElseThrow();
		pim.getModes();
		return null;
	}
}
