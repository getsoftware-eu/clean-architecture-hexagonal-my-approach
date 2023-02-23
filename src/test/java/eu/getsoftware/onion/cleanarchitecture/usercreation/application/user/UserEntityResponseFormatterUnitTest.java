package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterApplicationInteractorImpl;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterApplicationDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.UserResponseFormatter;

class UserEntityResponseFormatterUnitTest
{
    UserResponseFormatter userResponseFormatter = new UserResponseFormatter();
    UserRegisterApplicationDsGateway userDsGateway = mock(UserRegisterApplicationDsGateway.class);
    UserOutputApplicationPresenter userPresenter = mock(UserOutputApplicationPresenter.class);
    UserFactoryAggregate userFactoryAggregate = mock(UserFactoryAggregate.class);
    IUserInputApplicationBoundary IUserInputApplicationBoundary = new UserRegisterApplicationInteractorImpl(userDsGateway, userPresenter, userFactoryAggregate);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserResponseApplicationModelDTO modelResponse = new UserResponseApplicationModelDTO("baeldung", "2020-12-20T03:00:00.000");
        UserResponseApplicationModelDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.getCreationTime()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        UserRequestApplicationModelDTO userRequestApplicationModelDTO = new UserRequestApplicationModelDTO("baeldung", "123456");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("baeldung", "123456"));

        IUserInputApplicationBoundary.create(userRequestApplicationModelDTO);

        verify(userDsGateway).existsByName(userRequestModelArgumentCaptor.capture());
        String name = userRequestApplicationModelDTO.getName();
        assertEquals("baeldung", name);
    }
}