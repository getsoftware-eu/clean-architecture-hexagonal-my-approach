package eu.getsoftware.cleanarchitecture.application.port.user.out.iservice;

import eu.getsoftware.cleanarchitecture.application.port.user.out.dto.ResponseUserPortDTO;

/**
 * Its an interface for external adapter usage, therefore it is located in "application.port.out"
 */
public interface IUserResponseDTOPortPresenter
{
    ResponseUserPortDTO prepareSuccessView(ResponseUserPortDTO user);
    
    /**
     * Presenter have to assign response HttpCode to every error!
     * 
     * Eugen: but here we throw exception, but formal return type is DTO
     * 
     * @param error
     * @return
     */
    ResponseUserPortDTO prepareFailView(String error);
}
