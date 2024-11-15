package eu.getsoftware.cleanarchitecture.application.domain.usecase;

import eu.getsoftware.cleanarchitecture.adapter.out.UserResponseDTOPortFormatter;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.RegisterUserUseCaseImpl;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IRegisterUserUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.UserGatewayService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserEntityResponseFormatterUnitTest
{
//    UserDsRequestMapper userDsRequestMapper;
    UserResponseDTOPortFormatter userResponseFormatter = new UserResponseDTOPortFormatter();
    //we use internal useCase only for checking the test condition!!!
    IUserResponseDTOPortPresenter<UserClientDTO> userPresenter = mock(UserResponseDTOPortFormatter.class);
    private IUserResponseDTOPortPresenter userResponseDTOPresenter = new UserResponseDTOPortFormatter();
    private final IUserResponseDTOPortPresenter<UserClientDTO> userResponseDTOPortPresenter = mock(UserResponseDTOPortFormatter.class);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);
    private RegisterUserUseCaseImpl registerUserUseCase;
    private UserGatewayService userGatewayService;

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserClientDTO modelResponse = new UserClientDTO(UserDomainId.generate(), "eugen",  "username", "2020-12-20T03:00:00.000");
        UserClientDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.creationTime()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        UserRegisterRequestUseCaseDTO userRequestApplicationModelDTO = new UserRegisterRequestUseCaseDTO(123L, "eugen", "e@ma.il", "username", "123456", "-");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("eugen", "123456"));

        registerUserUseCase.execute(userRequestApplicationModelDTO);
        
        verify(userGatewayService).findByField("name", userRequestModelArgumentCaptor.capture()).isPresent();
        
        // then:
        String name = userRequestApplicationModelDTO.name();
        assertEquals("eugen", name);
    }
}