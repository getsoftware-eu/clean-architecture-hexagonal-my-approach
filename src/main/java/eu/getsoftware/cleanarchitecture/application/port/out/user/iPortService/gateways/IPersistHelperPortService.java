package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;

/**
 * eu: out.port can use entity, not only DTO (out port used only local im DDD)
 */
public interface IPersistHelperPortService<T extends IUserDomainEntity> {

    /**
     * any custom logik, that is too low for an UseCase Layer...
     */
    T createCustomEntityWithProps(UserRequestUseCaseDTO requestUserDto);
}

