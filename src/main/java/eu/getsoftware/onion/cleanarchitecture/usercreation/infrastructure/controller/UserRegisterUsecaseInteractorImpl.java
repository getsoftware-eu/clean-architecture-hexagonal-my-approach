package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases.UserRegisterUsecaseInteractorAbstr;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service.UserRegService;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUsecaseInteractorImpl extends UserRegisterUsecaseInteractorAbstr {

    private final UserRegService userRegService;

    /**
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param userOutputApplicationPresenter
     * @param userFactoryAggregate
     * @param userDsRequestMapper
     * @param userRegService
     */
    public UserRegisterUsecaseInteractorImpl(
//            IUserRegisterApplicationDsGatewayService userRegisterDfGateway, 
            IUserOutputApplicationPresenter userOutputApplicationPresenter,
            IUserFactoryAggregate<UserDataMapperEntity/*, UserDsRequestApplicationModelDTO*/> userFactoryAggregate,
            UserDsRequestMapper userDsRequestMapper,
            UserRegService userRegService) {
        super(/*userRegisterDfGateway,*/ userOutputApplicationPresenter, userFactoryAggregate, userDsRequestMapper, userRegService);
        
        this.userRegService = userRegService;
    }
}
