package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ObjectJpaRepository;
import ru.mokb.mars.requirements.database.model.LAObject;
import ru.mokb.mars.requirements.rest.controllers.SubsystemController;
import ru.mokb.mars.requirements.rest.responses.FetchSubsystemResponse;
import ru.mokb.mars.requirements.rest.responses.FetchSubsystemsResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubsystemControllerImpl implements SubsystemController {
	private final ObjectJpaRepository objectJpaRepository;


	@Override
	public FetchSubsystemsResponse fetchSubsystems(String objectId) {
		LAObject laObject = objectJpaRepository.findById(objectId).orElseThrow();

		List<FetchSubsystemResponse> fetchSubsystemResponses = laObject.getSubsystems().stream()
				.map(subsystem -> {
					FetchSubsystemResponse fetchSubsystemResponse = new FetchSubsystemResponse();
					fetchSubsystemResponse.setId(subsystem.getId());
					fetchSubsystemResponse.setName(subsystem.getName());
					return fetchSubsystemResponse;
				}).collect(Collectors.toList());

		FetchSubsystemsResponse fetchSubsystemsResponse = new FetchSubsystemsResponse();
		fetchSubsystemsResponse.setSubsystems(fetchSubsystemResponses);
		return fetchSubsystemsResponse;
	}
}
