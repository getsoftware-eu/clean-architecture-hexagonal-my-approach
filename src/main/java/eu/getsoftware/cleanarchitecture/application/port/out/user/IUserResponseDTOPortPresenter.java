package eu.getsoftware.cleanarchitecture.application.port.out.user;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;

/**
 * Its an interface for external adapter usage, therefore it is located in "application.port.out"
 */
public interface IUserResponseDTOPortPresenter
{
    UserResponseClientDTO prepareSuccessView(UserResponseClientDTO user);
    
    /**
     * Presenter have to assign response HttpCode to every error!
     * 
     * Eugen: but here we throw exception, but formal return type is DTO
     * 
     * @param error
     * @return
     */
    UserResponseClientDTO prepareFailView(String error);
}
