package eu.getsoftware.onion.cleanarchitecture.usercreation.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterInteractor;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.UserResponseFormatter;

class UserEntityResponseFormatterUnitTest
{
    UserResponseFormatter userResponseFormatter = new UserResponseFormatter();
    UserRegisterDsGateway userDsGateway = mock(UserRegisterDsGateway.class);
    UserOutputPresenter userPresenter = mock(UserOutputPresenter.class);
    UserFactoryAggregator userFactoryAggregator = mock(UserFactoryAggregator.class);
    UserInputBoundary userInputBoundary = new UserRegisterInteractor(userDsGateway, userPresenter, userFactoryAggregator);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserResponseModel modelResponse = new UserResponseModel("baeldung", "2020-12-20T03:00:00.000");
        UserResponseModel formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.getCreationTime()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        UserRequestModel userRequestModel = new UserRequestModel("baeldung", "123456");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("baeldung", "123456"));

        userInputBoundary.create(userRequestModel);

        verify(userDsGateway).existsByName(userRequestModelArgumentCaptor.capture());
        String name = userRequestModel.getName();
        assertEquals("baeldung", name);
    }
}