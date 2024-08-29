package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserEntityFactory;
import eu.getsoftware.cleanarchitecture.users.domain.model.IUserFactory;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases.IUserInputPortUseCase;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases.impl.UserInputPortUseCaseImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service.UserRegisterPortServiceImpl;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.springframework.web.server.ResponseStatusException;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.usecases.IUserRegisterAppUseCase;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service.UserResponseDTOPortFormatter;

class UserEntityResponseFormatterUnitTest
{
//    UserDsRequestMapper userDsRequestMapper;
    UserResponseDTOPortFormatter userResponseFormatter = new UserResponseDTOPortFormatter();
    IUserRegisterAppUseCase userDsGateway = mock(IUserRegisterAppUseCase.class);
    IUserResponseDTOPortPresenter userPresenter = mock(IUserResponseDTOPortPresenter.class);
    IUserFactory<UserMappedEntity/*, UserDsRequestApplicationModelDTO*/> userFactoryAggregate = mock(IUserFactory.class);
    UserRegisterPortServiceImpl userRegisterPortServiceImpl = mock(UserRegisterPortServiceImpl.class);
    private IUserFactory<UserMappedEntity> userFactory = mock(UserEntityFactory.class);
    private RequestUserAppDTOMapper requestUserDTOMapper = Mappers.getMapper(RequestUserAppDTOMapper.class);
    private IUserResponseDTOPortPresenter userResponseDTOPresenter = new UserResponseDTOPortFormatter();
    private UserRegisterPortServiceImpl userRegisterService = mock(UserRegisterPortServiceImpl.class);
    //    IUserInputPortUseCase IUserInputApplicationBoundary = new UserInputPortUseCaseImpl(userPresenter, userFactoryAggregate, , userRegisterPortServiceImpl);
    IUserInputPortUseCase IUserInputApplicationBoundary = new UserInputPortUseCaseImpl(userFactory, requestUserDTOMapper , userRegisterService, userResponseDTOPresenter);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        ResponseUserPortDTO modelResponse = new ResponseUserPortDTO("eugen", "2020-12-20T03:00:00.000");
        ResponseUserPortDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.creationTimeStr()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        RequestUserPortDTO userRequestApplicationModelDTO = new RequestUserPortDTO("eugen", "123456");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("eugen", "123456"));

        IUserInputApplicationBoundary.create(userRequestApplicationModelDTO);

        verify(userDsGateway).existsByName(userRequestModelArgumentCaptor.capture());
        String name = userRequestApplicationModelDTO.name();
        assertEquals("eugen", name);
    }
}