package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;

public interface IUserDTOExternalClientService<T extends IUserDomainEntity, Z extends IUserDomainDTO> {

    public Z convertToModelDTO(T entity);

    public UserResponseClientDTO convertToClientDTO(Z userModelDTO);
    
    public IUserDomainEntity createNewEntity(UserRequestUseCaseDTO userRequestDTO);
}
