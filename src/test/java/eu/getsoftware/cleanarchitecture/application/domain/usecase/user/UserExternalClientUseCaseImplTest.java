package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.UserResponseDTOPortFormatter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainserviceimpl.UserDTOServiceImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.UserPersistHelperPortServiceImpl;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IRegisterUserUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.IDomainPersistPortService;
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

    private final DomainEntityDTOServiceAbstr<UserMappedDBEntity, UserRegisterRequestUseCaseDTO, UserClientDTO> userDTOService = mock(UserDTOServiceImpl.class);
    private final IUserResponseDTOPortPresenter<UserClientDTO> userResponseDTOPortPresenter = mock(UserResponseDTOPortFormatter.class);
    private final IDomainPersistPortService<UserMappedDBEntity> persistHelperPortService = mock(UserPersistHelperPortServiceImpl.class);

    IRegisterUserUseCase registerUseCase = new RegisterUserUseCaseImpl(userDomainPersistService, userDTOService, userResponseDTOPortPresenter,persistHelperPortService);

    @Test
    void shouldThrowResponseStatusException() {
        
        // given:
        UserRegisterRequestUseCaseDTO requestDTO = new UserRegisterRequestUseCaseDTO(123, "name", "username", "123", "-");

        //eu: we can not create DbEntity, but only snapShot it from created DomainEntity
        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);

        // when:
        assertThatThrownBy(() -> registerUseCase.execute(requestDTO)).isInstanceOf(ResponseStatusException.class);
        
        // then:
        verify(userDomainPersistService, times(0)).saveEntity(any(UserMappedDBEntity.class)); //one time: we saved created entity 

    }
    
    @Test
    void checkInnerMethodTimesCalled() {
        
        // given:
        UserRegisterRequestUseCaseDTO requestDTO = new UserRegisterRequestUseCaseDTO(123, "name", "username", "123456", "-");

        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);

        // when:
        
        UserClientDTO responseDTO = registerUseCase.execute(requestDTO);
        UserClientDTO responseDTO2 = registerUseCase.execute(requestDTO);

        // then:
        assertThat(responseDTO2.isPasswordValid()).isFalse();
        assertThat(responseDTO2.name()).isEmpty();
        
        verify(userDomainPersistService, times(1)).saveEntity(any(UserMappedDBEntity.class)); //one time: we saved created entity 
        verify(userResponseDTOPortPresenter, times(1)).prepareSuccessView(any(UserClientDTO.class)); //one time: we processed the responseDto

    }

    @Test
    void checkInnerArgumentValueCalled() {

        // given:
        UserRegisterRequestUseCaseDTO requestDTO = new UserRegisterRequestUseCaseDTO(123, "name", "username", "123456", "-");

        UserMappedDBEntity checkEntity = (UserMappedDBEntity) UserMappedDBEntity.builder().name(requestDTO.name()).password(requestDTO.password()).creationTime(LocalDateTime.now()).build();
        when(userDTOService.createEntityFromDTO(requestDTO)).thenReturn(checkEntity);
        ArgumentCaptor<String> userRequestDtoArgumentCaptor = ArgumentCaptor.forClass(String.class); // eu: only STRING-ARGUMENTS


        UserClientDTO responseDTO = registerUseCase.execute(requestDTO);

        verify(userDomainPersistService).existsByName(userRequestDtoArgumentCaptor.capture()); //eu: capture inner method STRING-ARGUMENT call
        String capturedArgument = userRequestDtoArgumentCaptor.getValue(); //eu: value of inner called STRING-ARGUMENT ++++
        
        assertEquals("name", capturedArgument);
    }
    
}