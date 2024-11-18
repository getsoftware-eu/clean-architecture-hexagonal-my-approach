package eu.getsoftware.cleanarchitecture.application.port.out.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;

/**
 * Its an interface for external adapter usage, therefore it is located in "application.port.out"
 */

public interface IUserResponseDTOPortPresenter {

    IUserDomainResponseDTO prepareSuccessView(IUserDomainResponseDTO response);

    <T> T prepareFailView(String error);

    <T> T prepareFailView(Exception exception);

}
