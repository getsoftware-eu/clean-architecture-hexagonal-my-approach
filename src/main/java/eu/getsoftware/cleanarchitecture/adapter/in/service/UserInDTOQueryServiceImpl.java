package eu.getsoftware.cleanarchitecture.adapter.in.service;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.service.UserOutRepositoryQueryAdapter;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.DtoGenericMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice.UserInDTOQueryService;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInDTOQueryServiceImpl implements UserInDTOQueryService {

    private final UserOutRepositoryQueryAdapter userOutRepositoryQueryAdapter;
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;
    private final DtoGenericMapper<UserRootDomainEntity, UserClientDTO> userDtoMapper;
    
    @Override
    public UserClientDTO findExistingUserByName(String searchName)
    {
        Optional<UserRootDomainEntity> entityByNameOpt = userOutRepositoryQueryAdapter.findByField("name", searchName);

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
            entityById = userOutRepositoryQueryAdapter.findOrThrow(userId);
        }
        catch (EntityNotFoundException e) {
            return userResponseDTOPortPresenter.prepareFailView(e.getMessage());
        }

        UserClientDTO userResponseDTO = userDtoMapper.toDto(entityById);

        return userResponseDTO;
//        return formatModelDTOForClientView(userResponseDTO);
    }
}
