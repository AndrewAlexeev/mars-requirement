package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

import java.util.List;

@Data
public class FetchSubsystemsResponse {
	private List<FetchSubsystemResponse> subsystems;
}
