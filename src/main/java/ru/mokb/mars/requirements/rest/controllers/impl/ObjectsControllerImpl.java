package ru.mokb.mars.requirements.rest.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mokb.mars.requirements.database.ObjectJpaRepository;
import ru.mokb.mars.requirements.rest.controllers.ObjectsController;
import ru.mokb.mars.requirements.rest.responses.FetchObjectResponse;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;
import ru.mokb.mars.requirements.rest.utils.Objects2FetchObjectsConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ObjectsControllerImpl implements ObjectsController {
	private final ObjectJpaRepository objectJpaRepository;

	@Override
	public FetchObjectsResponse fetchObjects() {

		List<FetchObjectResponse> fetchObjectResponseList = objectJpaRepository.findAll().stream()
				.map(Objects2FetchObjectsConverter::convert)
				.collect(Collectors.toList());

		FetchObjectsResponse fetchObjectsResponse = new FetchObjectsResponse();
		fetchObjectsResponse.setObjects(fetchObjectResponseList);
		return fetchObjectsResponse;
	}
}
