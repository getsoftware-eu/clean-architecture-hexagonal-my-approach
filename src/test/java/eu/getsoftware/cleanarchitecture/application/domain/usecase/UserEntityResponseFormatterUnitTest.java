package eu.getsoftware.cleanarchitecture.application.domain.usecase;

import eu.getsoftware.cleanarchitecture.adapter.out.UserResponseDTOPortFormatter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserEntityFactory;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.UserDtoExternalClientServiceImpl;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.gateways.RegisterUserPortGatewayServiceImpl;
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRegisterDTOGateway;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserDTOExternalClientHelperService;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.UserExternalClientUseCaseImpl;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
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
    RegisterUserPortGatewayServiceImpl internalUserDsGateway = mock(RegisterUserPortGatewayServiceImpl.class);
    IUserResponseDTOPortPresenter<UserResponseClientDTO> userPresenter = mock(UserResponseDTOPortFormatter.class);
    IUserDomainFactory<UserMappedEntity/*, UserDsRequestApplicationModelDTO*/> userFactoryAggregate = mock(IUserDomainFactory.class);
    RegisterUserPortGatewayServiceImpl registerUserPortGatewayServiceImpl = mock(RegisterUserPortGatewayServiceImpl.class);
    private IUserDomainFactory<UserMappedEntity> userFactory = mock(UserEntityFactory.class);
    private RequestUserAppDTOMapper requestUserDTOMapper = Mappers.getMapper(RequestUserAppDTOMapper.class);
    private IUserResponseDTOPortPresenter userResponseDTOPresenter = new UserResponseDTOPortFormatter();
    private RegisterUserPortGatewayServiceImpl userRegisterService = mock(RegisterUserPortGatewayServiceImpl.class);
    //    IUserInputPortUseCase IUserInputApplicationBoundary = new UserInputPortUseCaseImpl(userPresenter, userFactoryAggregate, , userRegisterPortServiceImpl);
    private IUserDTOExternalClientHelperService<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDTOToExternalClientService = mock(UserDtoExternalClientServiceImpl.class);
    private IDomainRegisterDTOGateway<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDomainPersistService = mock(RegisterUserPortGatewayServiceImpl.class);
    private final IUserResponseDTOPortPresenter<UserResponseClientDTO> userResponseDTOPortPresenter = mock(UserResponseDTOPortFormatter.class);
    IUserExternalClientUseCase IUserInputApplicationBoundary = new UserExternalClientUseCaseImpl(userDTOToExternalClientService, userDomainPersistService, userResponseDTOPortPresenter);
    ArgumentCaptor<String> userRequestModelArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserResponseClientDTO modelResponse = new UserResponseClientDTO("eugen",  "username", "2020-12-20T03:00:00.000");
        UserResponseClientDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.creationTimeStr()).isEqualTo("03:00:00");
    }

    @Test
    void whenPrepareFailView_thenThrowHttpConflictException() {
        assertThatThrownBy(() -> userResponseFormatter.prepareFailView("Invalid password")).isInstanceOf(ResponseStatusException.class);
    }

    @Ignore
    @Test
    void whenCreateUser_thenSuccess() {

        UserRequestUseCaseDTO userRequestApplicationModelDTO = new UserRequestUseCaseDTO("eugen", "username", "123456", "-");
        //when(userFactoryAggregator.create(anyString(), anyString())).thenReturn(new CommonUserEntity("eugen", "123456"));

        IUserInputApplicationBoundary.registerNewUser(userRequestApplicationModelDTO);

        verify(internalUserDsGateway).existsByName(userRequestModelArgumentCaptor.capture());
        
        // then:
        String name = userRequestApplicationModelDTO.name();
        assertEquals("eugen", name);
    }
}