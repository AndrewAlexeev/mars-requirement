package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.LAObject;
import ru.mokb.mars.requirements.rest.responses.FetchObjectResponse;
import ru.mokb.mars.requirements.rest.responses.FetchObjectsResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Objects2FetchObjectsConverter {

	public static FetchObjectsResponse convert(List<LAObject> LAObjectList) {
		List<FetchObjectResponse> fetchObjectResponses = LAObjectList.stream()
				.map(Objects2FetchObjectsConverter::convert)
				.collect(Collectors.toList());
		FetchObjectsResponse fetchObjectsResponse = new FetchObjectsResponse();
		fetchObjectsResponse.setObjects(fetchObjectResponses);
		return fetchObjectsResponse;

	}

	public static FetchObjectResponse convert(LAObject LAObject) {
		FetchObjectResponse fetchObjectResponse = new FetchObjectResponse();
		fetchObjectResponse.setId(LAObject.getId());
		fetchObjectResponse.setOfficialName(LAObject.getOfficialName());
		return fetchObjectResponse;
	}

}
