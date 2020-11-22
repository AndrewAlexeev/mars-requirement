package ru.mokb.mars.requirements.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.mokb.mars.requirements.database.model.Mode;
import ru.mokb.mars.requirements.rest.responses.FetchModeResponse;
import ru.mokb.mars.requirements.rest.responses.FetchModesResponse;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Modes2FetchModesConverter {

	public static FetchModesResponse convert(List<Mode> modes) {
		List<FetchModeResponse> fetchModeResponses = modes.stream()
				.map(Modes2FetchModesConverter::convert)
				.collect(Collectors.toList());
		FetchModesResponse fetchModesResponse = new FetchModesResponse();
		fetchModesResponse.setModes(fetchModeResponses);
		return fetchModesResponse;

	}

	public static FetchModeResponse convert(Mode mode) {
		FetchModeResponse fetchModeResponse = new FetchModeResponse();
		fetchModeResponse.setId(mode.getId());
		fetchModeResponse.setSimulatedMode(mode.getSimulatedMode());
		return fetchModeResponse;
	}

}
