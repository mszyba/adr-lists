package eu.michalszyba.adrlist.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JsonConverter implements AttributeConverter<Object, String> {

    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (final JsonProcessingException e) {
            logger.error(String.valueOf(e));
        }
        return json;
    }

    @Override
    public DeliveryNoteForm convertToEntityAttribute(String json) {
        DeliveryNoteForm o = null;
        try {
            o = objectMapper.readValue(json, DeliveryNoteForm.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }
        return o;
    }
}
