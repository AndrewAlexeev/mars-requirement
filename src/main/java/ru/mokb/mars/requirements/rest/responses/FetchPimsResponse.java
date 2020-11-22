package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FetchPimsResponse {
private List<FetchPimResponse> pims = new ArrayList<>();
}
