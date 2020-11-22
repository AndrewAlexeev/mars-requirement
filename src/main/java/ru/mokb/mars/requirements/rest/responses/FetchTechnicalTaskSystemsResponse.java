package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

import java.util.List;

@Data
public class FetchTechnicalTaskSystemsResponse {
private List<FetchTechnicalTaskSystemResponse> systems;
}
