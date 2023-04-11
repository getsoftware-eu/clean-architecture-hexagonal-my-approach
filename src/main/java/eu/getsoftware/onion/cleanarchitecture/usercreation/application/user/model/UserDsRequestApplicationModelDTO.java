package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import java.time.LocalDateTime;

/**
 * DOMAIN representation: of found user as DTO (Data Source)
 * @param name
 * @param password
 * @param creationTime
 */
public record UserDsRequestApplicationModelDTO(
        String name,
        String password,
        LocalDateTime creationTime
) {}
