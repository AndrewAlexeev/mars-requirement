package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ModeJpaRepository;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.ModesController;
import ru.mokb.mars.requirements.rest.requests.ModeAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModesControllerImpl implements ModesController {

	private final PIMJpaRepository pimJpaRepository;
	private final ModeJpaRepository modeJpaRepository;

	@Override
	public Integer addMode(ModeAddRequest modeAddRequest) {
		Mode mode = new Mode();
		mode.setName(modeAddRequest.getName());
		Mode savedMode = modeJpaRepository.save(mode);
		if (!CollectionUtils.isEmpty(modeAddRequest.getPimIds())) {
			List<PIM> pimList = pimJpaRepository.findAllById(modeAddRequest.getPimIds());
			pimList.forEach(pim -> pim.getModes().add(savedMode));
			pimJpaRepository.saveAll(pimList);
		}
		return savedMode.getId();
	}

	@Override
	public FetchObjectsResponse fetchModes() {
		return null;
	}
}
