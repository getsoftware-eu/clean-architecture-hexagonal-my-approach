package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRequestUseCaseDTO;

/**
 * eu: out.port can use entity, not only DTO (out port used only local im DDD)
 */
public interface IPersistHelperPortService<T extends UserDomainEntity> {

    /**
     * any custom logik, that is too low for an UseCase Layer...
     */
    T createCustomEntityWithProps(UserRequestUseCaseDTO requestUserDto);
}

