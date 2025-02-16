package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserDtoMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserUpdateRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.UserCrudUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.UserGatewayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@Service
@RequiredArgsConstructor
public class UserCrudUseCaseImpl implements UserCrudUseCase
{
    private final UserGatewayService userGatewayService;
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;
    private final UserDtoMapper userDtoMapper;
    

    @Override 
    public UserClientDTO findExistingUserByName(String searchName)
    {
        Optional<UserRootDomainEntity> entityByNameOpt = userGatewayService.findByField("name", searchName);

        if (entityByNameOpt.isEmpty()) {
            return userResponseDTOPortPresenter.prepareFailView("User not exists.");
        }

        UserRootDomainEntity domainEntity = entityByNameOpt.get();

        return userDtoMapper.toDto(domainEntity);

//        return formatModelDTOForClientView(userResponseDTO);
    }

    @Override 
    public UserClientDTO updateExistingUser(UserUpdateRequestUseCaseDTO requestModel)
    {
        UserRootDomainEntity entity;
        
        try{
          entity = userGatewayService.findOrThrow(requestModel.domainId());
        }
        catch (EntityNotFoundException e) {
            return userResponseDTOPortPresenter.prepareFailView(e.getMessage());
        }

        //eu: how to automatisieren?
        if(!Objects.equals(entity.getEmail(), requestModel.email()))
        {
            entity.setEmail( requestModel.email());
        }
        
        userGatewayService.saveToDb(entity);
        
        return userDtoMapper.toDto(entity);

//        return formatModelDTOForClientView(userResponseDTO);
    }

    @Override
    public UserClientDTO updateUserAddress(UserDomainId userDomainId, AddressValueObject addressValueObject) {

        UserRootDomainEntity entityById = userGatewayService.findOrThrow(userDomainId);

        entityById.changeAddress(addressValueObject);

        UserClientDTO userResponseDTO = userDtoMapper.toDto(entityById);

        return userResponseDTO;
    }

    @Override 
    public UserClientDTO findExistingUserByDomainId(UserDomainId userId)
    {
        UserRootDomainEntity entityById;
        
        try {
              entityById = userGatewayService.findOrThrow(userId);
        }
        catch (EntityNotFoundException e) {
            return userResponseDTOPortPresenter.prepareFailView(e.getMessage());
        }

        UserClientDTO userResponseDTO = userDtoMapper.toDto(entityById);

        return userResponseDTO;
//        return formatModelDTOForClientView(userResponseDTO);
    }
}
