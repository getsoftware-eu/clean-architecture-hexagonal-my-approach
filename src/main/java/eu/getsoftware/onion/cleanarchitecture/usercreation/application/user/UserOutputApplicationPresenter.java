package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

public interface UserOutputApplicationPresenter
{
    UserResponseApplicationModelDTO prepareSuccessView(UserResponseApplicationModelDTO user);

    UserResponseApplicationModelDTO prepareFailView(String error);
}
