package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ObjectJpaRepository;
import ru.mokb.mars.requirements.database.PIMJpaRepository;
import ru.mokb.mars.requirements.database.model.LAObject;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.controllers.PimsController;
import ru.mokb.mars.requirements.rest.responses.FetchPimsResponse;
import ru.mokb.mars.requirements.rest.utils.Pims2FetchPimsConverter;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PimsControllerImpl implements PimsController {
	private final PIMJpaRepository pimJpaRepository;
	private final ObjectJpaRepository objectJpaRepository;

	@Override
	public FetchPimsResponse fetchPIMs(String objectId) {
		LAObject LAObject = objectJpaRepository.findById(objectId).orElseThrow();

		List<PIM> pims = LAObject.getPims();
		return Pims2FetchPimsConverter.convert(pims);
	}
}
