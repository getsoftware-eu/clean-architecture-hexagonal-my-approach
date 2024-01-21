package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO;

import java.time.LocalDateTime;

/**
 * DOMAIN representation: of found user as DTO (Data Source)
 * @param name
 * @param password
 */
public record UserDsRequestApplicationModelDTO (
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
