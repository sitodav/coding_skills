package sito.davide.utils.dateserial;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class  DataConSecondiDeserializer extends JsonDeserializer<Date> {

	private static Logger log = LoggerFactory.getLogger(DataConSecondiDeserializer.class);

    @Override
    public Date deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	 
        if (jp.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
            try {
                Date date = format.parse(jp.getText().toString());
                return date;
            } catch (Exception e) {
                log.error("Error",e);
            }
        }
        return null;
    }

}