package eu.getsoftware.cleanarchitecture.application.port.in.user.dto;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IDomainRequestDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * 
 * create Record in adapter package, but dtoInterface in useCase package!!
 * 
 * REQUEST Representation:
 * every requestDTO has the name of requester and its password???  requester is active-session-user
 * @param name
 * @param password
 */
public record UserUpdateRequestUseCaseDTO(
    
	long requesterId,

	UserDomainId domainId,
	
	@NotBlank(message = "Name cannot be blank")
	String name,

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	String email,
	
	String username,

	@Size(min = 8, message = "{validation.name.size.too_short}")
	@Size(max = 200, message = "{validation.name.size.too_long}")	
	String password,

	String specialFieldForUseCase
) implements IDomainRequestDTO {

	private final static LocalDateTime creationTime = LocalDateTime.now();

//	@Override
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void validateBusinessLogic() {
		if (name.length() < 3) {
			throw new IllegalArgumentException("Name must be at least 3 characters long");
		}
		if (password.equalsIgnoreCase("password")) {
			throw new IllegalArgumentException("Password cannot be 'password'");
		}
	}
}
