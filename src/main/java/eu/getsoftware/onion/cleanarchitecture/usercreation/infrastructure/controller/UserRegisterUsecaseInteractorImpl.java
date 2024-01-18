package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases.UserRegisterUsecaseInteractorAbstr;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service.UserRegService;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUsecaseInteractorImpl extends UserRegisterUsecaseInteractorAbstr {

    private final UserRegService userRegService;

    public UserRegisterUsecaseInteractorImpl(
//            IUserRegisterApplicationDsGatewayService userRegisterDfGateway, 
            IUserOutputApplicationPresenter userOutputApplicationPresenter,
            IUserFactoryAggregate userFactoryAggregate,
            UserDsRequestMapper userDsRequestMapper,
            UserRegService userRegService) {
        super(/*userRegisterDfGateway,*/ userOutputApplicationPresenter, userFactoryAggregate, userDsRequestMapper, userRegService);
        
        this.userRegService = userRegService;
    }
}
