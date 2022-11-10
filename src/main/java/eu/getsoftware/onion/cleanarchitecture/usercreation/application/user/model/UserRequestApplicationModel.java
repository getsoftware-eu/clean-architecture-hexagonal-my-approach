package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@Getter 
@RequiredArgsConstructor
public class UserRequestApplicationModel
{
    private final String name;
	private final String password;
}
