package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@Getter 
@RequiredArgsConstructor
public class UserResponseApplicationModel
{
    private final String login;
    private final String creationTime;
}
