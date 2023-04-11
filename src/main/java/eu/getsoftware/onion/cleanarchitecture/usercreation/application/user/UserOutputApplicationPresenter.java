package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

public interface UserOutputApplicationPresenter
{
    UserResponseApplicationModelDTO prepareSuccessView(UserResponseApplicationModelDTO user);
    
    /**
     * Presenter have to assign response HttpCode to every error!
     * 
     * Eugen: but here we throw exception, but formal return type is DTO
     * 
     * @param error
     * @return
     */
    UserResponseApplicationModelDTO prepareFailView(String error);
}
