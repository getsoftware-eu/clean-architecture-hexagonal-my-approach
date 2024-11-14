package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways.IPersistHelperPortService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPersistHelperPortServiceImpl implements IPersistHelperPortService<UserMappedDBEntity> {
    
    private final DomainEntityGatewayServiceAbstr<UserMappedDBEntity> userDomainPersistService;

    /**
     * any custom logik, that is too low for domain layer and a UseCase...
     */
    @NotNull
    @Override
    public UserMappedDBEntity createCustomEntityWithProps(UserRequestUseCaseDTO requestUserDto) {
        UserMappedDBEntity newUserEntity = userDomainPersistService.createEntity(requestUserDto.name());
        /**
         * this action implemented ONLY for user, no other domain has password!!
         */
        newUserEntity.setInitValues(requestUserDto.name(), requestUserDto.password());
        /**
         * we don't persist, just create
         */
        // userDomainPersistService.saveEntity(newUserEntity);
        return newUserEntity;
    }
}
