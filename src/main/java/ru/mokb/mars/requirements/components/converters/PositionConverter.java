package ru.mokb.mars.requirements.components.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.mokb.mars.requirements.database.model.Position;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Slf4j
@Converter
@RequiredArgsConstructor
public class PositionConverter  implements AttributeConverter<Position, String> {

	private ObjectMapper jsonMapper = JsonDataConverterHelper.createObjectMapper();

	@Override
	public String convertToDatabaseColumn(Position position) {
		if (position == null) {
			return null;
		}
		String json = null;
		try {
			json = jsonMapper.writeValueAsString(position);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return json;
	}

	@Override
	public Position convertToEntityAttribute(String json) {
		if (json == null) {
			return null;
		}
		Position position = null;
		try {
			position = jsonMapper.readValue(json, Position.class);
		} catch (IOException e) {
			log.error(e.getMessage() + "\nJSON is [{}]", json, e);
		}
		return position;
	}
}
