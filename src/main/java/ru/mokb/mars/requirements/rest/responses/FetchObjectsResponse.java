package ru.mokb.mars.requirements.rest.responses;

import lombok.Data;
import ru.mokb.mars.requirements.rest.responses.FetchObjectResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class FetchObjectsResponse {
private List<FetchObjectResponse> objects = new ArrayList<>();
}
