package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseModel;

public interface UserOutputPresenter
{
    UserResponseModel prepareSuccessView(UserResponseModel user);

    UserResponseModel prepareFailView(String error);
}
