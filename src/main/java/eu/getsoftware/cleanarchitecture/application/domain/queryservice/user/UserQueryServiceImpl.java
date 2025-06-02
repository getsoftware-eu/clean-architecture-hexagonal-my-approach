package eu.getsoftware.cleanarchitecture.application.domain.queryservice.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.DtoGenericMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice.UserInDTOQueryService;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.UserGatewayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserInDTOQueryService {

    private final UserGatewayService userGatewayService;
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;
    private final DtoGenericMapper<UserRootDomainEntity, UserClientDTO> userDtoMapper;
    
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
