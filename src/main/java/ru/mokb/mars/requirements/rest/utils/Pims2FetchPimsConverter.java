package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.PIM;
import ru.mokb.mars.requirements.rest.responses.FetchPimResponse;
import ru.mokb.mars.requirements.rest.responses.FetchPimsResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pims2FetchPimsConverter {

	public static FetchPimsResponse convert(List<PIM> pims) {
		List<FetchPimResponse> fetchPimResponses = pims.stream()
				.map(Pims2FetchPimsConverter::convert)
				.collect(Collectors.toList());
		FetchPimsResponse fetchPimsResponse = new FetchPimsResponse();
		fetchPimsResponse.setPims(fetchPimResponses);
		return fetchPimsResponse;

	}

	public static FetchPimResponse convert(PIM pim) {
		FetchPimResponse fetchPimResponse = new FetchPimResponse();
		fetchPimResponse.setId(pim.getId());
		fetchPimResponse.setName(pim.getName());
		return fetchPimResponse;
	}

}
