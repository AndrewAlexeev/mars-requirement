package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ModeJpaRepository;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.ModesController;
import ru.mokb.mars.requirements.rest.requests.FetchModesRequest;
import ru.mokb.mars.requirements.rest.responses.FetchModesResponse;
import ru.mokb.mars.requirements.rest.utils.Modes2FetchModesConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModesControllerImpl implements ModesController {

	private final PIMJpaRepository pimJpaRepository;
	private final ModeJpaRepository modeJpaRepository;

	@Override
	public FetchModesResponse fetchModes(Double pimId) {
		PIM pim = pimJpaRepository.findById(pimId).orElseThrow();
		List<Mode> modes = pim.getModes();
		return Modes2FetchModesConverter.convert(modes);
	}
}
