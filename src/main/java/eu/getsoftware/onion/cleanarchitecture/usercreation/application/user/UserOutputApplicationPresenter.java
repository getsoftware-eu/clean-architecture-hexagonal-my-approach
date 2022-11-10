package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModel;

public interface UserOutputApplicationPresenter
{
    UserResponseApplicationModel prepareSuccessView(UserResponseApplicationModel user);

    UserResponseApplicationModel prepareFailView(String error);
}
