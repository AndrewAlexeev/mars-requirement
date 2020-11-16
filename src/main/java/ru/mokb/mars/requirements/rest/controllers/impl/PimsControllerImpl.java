package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ModeJpaRepository;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.PimsController;
import ru.mokb.mars.requirements.rest.requests.PimAddRequest;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PimsControllerImpl implements PimsController {
	private final PIMJpaRepository pimJpaRepository;
	private final ModeJpaRepository modeJpaRepository;

	@Override
	public Integer addPIM(PimAddRequest pimAddRequest) {
		PIM pim = new PIM();
		pim.setName(pimAddRequest.getName());
		if(!CollectionUtils.isEmpty(pimAddRequest.getModeIds())){
			List<Mode> modeList = modeJpaRepository.findAllById(pimAddRequest.getModeIds());
			pim.setModes(modeList);
		}
		PIM savedPim = pimJpaRepository.save(pim);
		return savedPim.getId();
	}

	@Override
	public FetchObjectsResponse fetchPIMs() {
		return null;
	}
}
