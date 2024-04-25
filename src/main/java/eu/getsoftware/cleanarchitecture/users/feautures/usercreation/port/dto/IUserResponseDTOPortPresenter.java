package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto;

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
