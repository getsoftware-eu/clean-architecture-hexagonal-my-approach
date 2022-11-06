package eu.getsoftware.onion.cleanarchitecture.usercreation.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterInteractor;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;

@Configuration
public class ApplicationConfiguration
{
//  @Bean
//  public UserInputBoundary useCaseService(UserRegisterDsGateway userRegisterDfGateway, UserOutputPresenter userOutputPresenter,
//          UserFactoryAggregator userFactoryAggregator) {
//    return new UserRegisterInteractor(userRegisterDfGateway, userOutputPresenter, userFactoryAggregator);
//  }
}
