package br.com.ocorrencias.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		return(value != null && value) ? "S" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return "Y".equals(value);
	}
}
