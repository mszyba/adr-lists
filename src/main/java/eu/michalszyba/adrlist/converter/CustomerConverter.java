package eu.michalszyba.adrlist.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.michalszyba.adrlist.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class CustomerConverter implements AttributeConverter<Object, String> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object customerInfo) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            System.out.println();
        }

        return customerInfoJson;    }

    @Override
    public Customer convertToEntityAttribute(String customerInfoJSON) {

        Customer customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON, Customer.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return customerInfo;
    }


}
