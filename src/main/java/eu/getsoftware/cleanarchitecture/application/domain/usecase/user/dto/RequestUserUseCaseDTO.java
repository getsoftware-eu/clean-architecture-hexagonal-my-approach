package eu.getsoftware.cleanarchitecture.application.domain.usecase.user.dto;


import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDTO;

import java.time.LocalDateTime;

/**
 * DOMAIN representation: of found user as DTO (ds: Data Source)
 * @param name
 * @param password
 */
public record RequestUserUseCaseDTO(
        String name,
        String password
) implements IUserDTO {

    static LocalDateTime creationTime = LocalDateTime.now();
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
