package eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRequestUseCaseDTO;

public interface IUserDTOExternalClientHelperService<T extends UserDomainEntity, I extends IUserDomainRequestDTO, O extends IUserDomainResponseDTO> {

//    I convertToRequestDTO(T entity);

    T createNewEntity(UserRequestUseCaseDTO userRequestDTO);

    O convertToResponseDTO(T newUserEntity);
}
