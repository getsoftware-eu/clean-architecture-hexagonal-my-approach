package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

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
public record UserRequestUseCaseDTO(
    @NotNull
	String name,

//	@UniqueUsername(message = "{Unique.user.username}")
	String username,

	@Size(min = 8, message = "{validation.name.size.too_short}")
	@Size(max = 200, message = "{validation.name.size.too_long}")	
	String password,

	String specialFieldForUseCase
) implements IUserDomainDTO {

	private final static LocalDateTime creationTime = LocalDateTime.now();

//	@Override
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
}
