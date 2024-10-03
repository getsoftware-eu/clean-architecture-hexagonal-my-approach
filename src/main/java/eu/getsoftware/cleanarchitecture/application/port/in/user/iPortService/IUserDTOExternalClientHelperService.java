package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;

public interface IUserDTOExternalClientHelperService<T extends IUserDomainEntity, I extends IUserDomainRequestDTO, O extends IUserDomainResponseDTO> {

    I convertToRequestDTO(T entity);

    O convertToResponseDTO(I userRequestDTO);
    
    IUserDomainEntity createNewEntity(UserRequestUseCaseDTO userRequestDTO);
}
