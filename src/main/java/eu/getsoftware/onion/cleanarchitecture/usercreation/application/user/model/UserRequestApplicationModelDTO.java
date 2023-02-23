package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@Getter 
@RequiredArgsConstructor
public class UserRequestApplicationModelDTO
{
    private final String name;
	private final String password;
}
