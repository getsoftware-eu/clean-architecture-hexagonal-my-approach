package eu.getsoftware.onion.cleanarchitecture.usercreation.main.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterInteractor;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;

/**
 *  Create our remaining dependencies and inject them into our project:
 *  it decouples our business from the DI framework
 */
@Configuration
public class ApplicationConfiguration
{

}
