package com.catrion.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateStringDeserializer extends JsonDeserializer<Date> {
	
	final Logger logger = Logger.getLogger(DateStringDeserializer.class);

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
			throws IOException, JsonProcessingException {
		String str = paramJsonParser.getText().trim();

		try {
			logger.info("Inside DateStringDeserializer " + str);
			DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			if("".equals(str) || null== str || "null"== str) {
				return null;
			}
			Date fDate = originalFormat.parse(str);
			logger.info("Return DateStringDeserializer " + originalFormat.parse(str));
			return fDate;
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return paramDeserializationContext.parseDate(str);
	}

}