package eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IDomainRequestDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;

public interface IUserDTOExternalClientHelperService<T extends UserRootDomainEntity, I extends IDomainRequestDTO, O extends IUserDomainResponseDTO> {

//    I convertToRequestDTO(T entity);

    T createNewEntity(UserRegisterRequestUseCaseDTO userRequestDTO);

    O convertToResponseDTO(T newUserEntity);
}
