package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.UserResponseDTOPortFormatter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainServiceImpl.UserDTOServiceImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainServiceImpl.UserGatewayServiceImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.UserPersistHelperPortServiceImpl;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways.IPersistHelperPortService;
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

    private final DomainEntityGatewayServiceAbstr<UserMappedDBEntity> userDomainPersistService = mock(UserGatewayServiceImpl.class);
    private final DomainEntityDTOServiceAbstr<UserMappedDBEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDTOService = mock(UserDTOServiceImpl.class);
    private final IUserResponseDTOPortPresenter<UserResponseClientDTO> userResponseDTOPortPresenter = mock(UserResponseDTOPortFormatter.class);
    private final IPersistHelperPortService<UserMappedDBEntity> persistHelperPortService = mock(UserPersistHelperPortServiceImpl.class);

    IUserExternalClientUseCase registerUseCase = new UserExternalClientUseCaseImpl(userDomainPersistService, userDTOService, userResponseDTOPortPresenter,persistHelperPortService);

    @Test
    void shouldThrowResponseStatusException() {
        
        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO(123, "name", "username", "123", "-");

        //eu: we can not create DbEntity, but only snapShot it from created DomainEntity
        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);

        // when:
        assertThatThrownBy(() -> registerUseCase.registerNewUser(requestDTO)).isInstanceOf(ResponseStatusException.class);
        
        // then:
        verify(userDomainPersistService, times(0)).saveEntity(any(UserMappedDBEntity.class)); //one time: we saved created entity 

    }
    
    @Test
    void checkInnerMethodTimesCalled() {
        
        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO(123, "name", "username", "123456", "-");

        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);

        // when:
        
        UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);
        UserResponseClientDTO responseDTO2 = registerUseCase.registerNewUser(requestDTO);

        // then:
        assertThat(responseDTO2.isPasswordValid()).isFalse();
        assertThat(responseDTO2.name()).isEmpty();
        
        verify(userDomainPersistService, times(1)).saveEntity(any(UserMappedDBEntity.class)); //one time: we saved created entity 
        verify(userResponseDTOPortPresenter, times(1)).prepareSuccessView(any(UserResponseClientDTO.class)); //one time: we processed the responseDto

    }

    @Test
    void checkInnerArgumentValueCalled() {

        // given:
        UserRequestUseCaseDTO requestDTO = new UserRequestUseCaseDTO(123, "name", "username", "123456", "-");

        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);
        ArgumentCaptor<String> userRequestDtoArgumentCaptor = ArgumentCaptor.forClass(String.class); // eu: only STRING-ARGUMENTS


        UserResponseClientDTO responseDTO = registerUseCase.registerNewUser(requestDTO);

        verify(userDomainPersistService).existsByName(userRequestDtoArgumentCaptor.capture()); //eu: capture inner method STRING-ARGUMENT call
        String capturedArgument = userRequestDtoArgumentCaptor.getValue(); //eu: value of inner called STRING-ARGUMENT ++++
        
        assertEquals("name", capturedArgument);
    }
    
}