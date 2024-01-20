package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputUsecaseBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller.UserRegisterUsecaseInteractorImpl;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service.UserRegService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserRegisterApplicationDsGatewayService;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.UserResponseFormatter;

class UserEntityResponseFormatterUnitTest
{
    UserDsRequestMapper userDsRequestMapper;
    UserResponseFormatter userResponseFormatter = new UserResponseFormatter();
    IUserRegisterApplicationDsGatewayService userDsGateway = mock(IUserRegisterApplicationDsGatewayService.class);
    IUserOutputApplicationPresenter userPresenter = mock(IUserOutputApplicationPresenter.class);
    IUserFactoryAggregate<UserDataMapperEntity, UserDsRequestApplicationModelDTO> userFactoryAggregate = mock(IUserFactoryAggregate.class);
    UserRegService userRegService = mock(UserRegService.class);
    IUserInputUsecaseBoundary IUserInputApplicationBoundary = new UserRegisterUsecaseInteractorImpl(/*userDsGateway,*/ userPresenter, userFactoryAggregate, userDsRequestMapper, userRegService);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserResponseApplicationModelDTO modelResponse = new UserResponseApplicationModelDTO("eugen", "2020-12-20T03:00:00.000");
        UserResponseApplicationModelDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.creationTimeStr()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        UserRequestApplicationModelDTO userRequestApplicationModelDTO = new UserRequestApplicationModelDTO("eugen", "123456");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("eugen", "123456"));

        IUserInputApplicationBoundary.create(userRequestApplicationModelDTO);

        verify(userDsGateway).existsByName(userRequestModelArgumentCaptor.capture());
        String name = userRequestApplicationModelDTO.name();
        assertEquals("eugen", name);
    }
}