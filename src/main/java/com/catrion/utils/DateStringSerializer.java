package com.catrion.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
 
public class DateStringSerializer extends JsonSerializer<Date> {
	final Logger logger = Logger.getLogger(DateStringSerializer.class);
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (value != null) {        
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String formatted = sdf.format(value);
            logger.info("DateStringSerializer formatted value : "+formatted);
            gen.writeString(formatted);
        }else {
        	logger.info("DateStringSerializer formatted value is null ");
        }
    }
 
}