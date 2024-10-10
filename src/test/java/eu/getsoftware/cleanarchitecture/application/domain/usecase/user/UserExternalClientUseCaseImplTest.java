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
import org.mockito.ArgumentCaptor;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertThatThrownBy(() -> registerUseCase.registerNewUser(requestDTO)).isInstanceOf(ResponseStatusException.class);
        
        // then:
        verify(userDomainPersistService, times(0)).saveEntity(any(UserMappedEntity.class)); //one time: we saved created entity 

    }
    
    @Test
    void checkInnerMethodTimesCalled() {
        
        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO("name", "username", "123456", "-");
        when(userDTOToExternalClientService.createNewEntity(requestDTO)).thenReturn(new UserMappedEntity(requestDTO.name(), requestDTO.password(), LocalDateTime.now()));

        // when:
        
        UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);
        UserResponseClientDTO responseDTO2 = registerUseCase.registerNewUser(requestDTO);

        // then:
        assertThat(responseDTO2.isPasswordValid()).isFalse();
        assertThat(responseDTO2.name()).isEmpty();
        
        verify(userDomainPersistService, times(1)).saveEntity(any(UserMappedEntity.class)); //one time: we saved created entity 
        verify(userResponseDTOPortPresenter, times(1)).prepareSuccessView(any(UserResponseClientDTO.class)); //one time: we processed the responseDto

    }

    @Test
    void checkInnerArgumentValueCalled() {

        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO("name", "username", "123456", "-");
        when(userDTOToExternalClientService.createNewEntity(requestDTO)).thenReturn(new UserMappedEntity(requestDTO.name(), requestDTO.password(), LocalDateTime.now()));
        ArgumentCaptor<String> userRequestDtoArgumentCaptor = ArgumentCaptor.forClass(String.class); // eu: only STRING-ARGUMENTS


        UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);

        verify(userDomainPersistService).existsByName(userRequestDtoArgumentCaptor.capture()); //eu: capture inner method STRING-ARGUMENT call
        String capturedArgument = userRequestDtoArgumentCaptor.getValue(); //eu: value of inner called STRING-ARGUMENT ++++
        
        assertEquals("name", capturedArgument);
    }
    
}