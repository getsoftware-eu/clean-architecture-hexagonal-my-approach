package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.UserResponseDTOPortFormatter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.UserDtoExternalClientServiceImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.gateways.RegisterUserPortGatewayServiceImpl;
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRegisterDTOGateway;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserDTOExternalClientHelperService;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.UserExternalClientUseCaseImpl;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserExternalClientUseCaseImplTest {

    private final IUserDTOExternalClientHelperService<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDTOToExternalClientService = mock(UserDtoExternalClientServiceImpl.class);
    private final IDomainRegisterDTOGateway<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDomainPersistService = mock(RegisterUserPortGatewayServiceImpl.class);
    private final IUserResponseDTOPortPresenter<UserResponseClientDTO> userResponseDTOPortPresenter = mock(UserResponseDTOPortFormatter.class);

    IUserExternalClientUseCase registerUseCase = new UserExternalClientUseCaseImpl(userDTOToExternalClientService, userDomainPersistService, userResponseDTOPortPresenter);

    @Test
    void shouldThrowResponseStatusException() {
        
        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO("name", "username", "123", "-");
        when(userDTOToExternalClientService.createNewEntity(requestDTO)).thenReturn(new UserMappedEntity(requestDTO.name(), requestDTO.password(), LocalDateTime.now()));

        // when:
        assertThrows(ResponseStatusException.class, () -> {
            UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);
        });
        // then:
    }
    
    @Test
    void givenRequestDto_whenDoubleCreation_thenIsFalse() {
        
        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO("name", "username", "123456", "-");
        when(userDTOToExternalClientService.createNewEntity(requestDTO)).thenReturn(new UserMappedEntity(requestDTO.name(), requestDTO.password(), LocalDateTime.now()));

        // when:
        
        UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);
        
        UserResponseClientDTO responseDTO2 = registerUseCase.registerNewUser(requestDTO);

        // then:
        assertThat(responseDTO2.isPasswordValid()).isFalse();
        assertThat(responseDTO2.name()).isEmpty();
    }
    
}