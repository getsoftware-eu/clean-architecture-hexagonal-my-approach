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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterInteractor;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;

@Configuration
@EnableJpaRepositories(basePackages = { "eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository" })
public class InfrastructureConfiguration
{
	@Bean
	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
		return beanFactory -> {
			genericApplicationContext((BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
		};
	}
	
	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
		ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
		beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
		beanDefinitionScanner.scan("eu.getsoftware.onion.cleanarchitecture.usercreation");
	}
	
	static TypeFilter removeModelAndEntitiesFilter() {
		return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
				.getClassName()
				.endsWith("Model");
	}
}
