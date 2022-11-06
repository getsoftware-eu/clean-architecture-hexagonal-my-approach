package eu.getsoftware.onion.cleanarchitecture.usercreation.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterInteractor;

@Configuration
public class ApplicationConfiguration
{
  @Bean
  public UserInputBoundary apiService() {
    return new UserRegisterInteractor();
  }
}
