package eu.getsoftware.cleanarchitecture.application.domain.model.address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.domain.BusinessException;
import jakarta.validation.constraints.NotEmpty;

/**
 * Eugen: maybe nur ValueObject is record
 */
//@Value(staticConstructor="from")
public record AddressValueObject(
        @NotEmpty String street,
        @NotEmpty String city
) {

    public AddressValueObject {
        validateBusinessLogic(city);
    }

    public static AddressValueObject from(@NotEmpty String city, @NotEmpty String street) {
        return new AddressValueObject(city, street);
    }

    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize record", e);
        }
    }

    private void validateBusinessLogic(String testcity) {
        if("dummy".equals(testcity))
            throw new BusinessException("wrong city");
    }
}
