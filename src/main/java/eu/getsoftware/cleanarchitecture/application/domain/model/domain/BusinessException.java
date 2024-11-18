package eu.getsoftware.cleanarchitecture.application.domain.model.domain;

/**
 * В Domain Layer исключения (например, IllegalArgumentException, BusinessException) могут быть выброшены в случаях, когда нарушены бизнес-правила. 
 * Эти исключения должны быть специфичными для вашей бизнес-логики.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super();
    }
}