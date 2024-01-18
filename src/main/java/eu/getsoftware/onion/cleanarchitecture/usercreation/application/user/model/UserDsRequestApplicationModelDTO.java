package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO;

import java.time.LocalDateTime;

/**
 * DOMAIN representation: of found user as DTO (Data Source)
 * @param name
 * @param password
 * @param creationTime
 */
public record UserDsRequestApplicationModelDTO (
        String name,
        String password,
        LocalDateTime creationTime
) implements IUserDTO {
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
