package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import java.time.LocalDateTime;

public record UserDsRequestApplicationModelDTO(
        String name,
        String password,
        LocalDateTime creationTime
) {}
