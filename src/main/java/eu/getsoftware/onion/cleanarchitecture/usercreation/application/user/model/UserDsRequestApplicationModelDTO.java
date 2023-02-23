package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@Getter 
@RequiredArgsConstructor
public class UserDsRequestApplicationModelDTO
{
    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
}
