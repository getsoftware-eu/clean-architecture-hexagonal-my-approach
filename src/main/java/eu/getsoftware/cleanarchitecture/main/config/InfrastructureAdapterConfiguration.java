package eu.getsoftware.cleanarchitecture.main.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//eu: scan custom persistence Beans in external "adapter" package!
@EnableJpaRepositories(basePackages = { "eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository" })
@EntityScan(basePackages = {"eu.getsoftware.cleanarchitecture.adapter.out.persistence.model"})
public class InfrastructureAdapterConfiguration
{

    /**
     * 
     * цель: для ЧИСТЫХ domain-классов получить Spring Beans!!
     * хотя мы не пишем @Component
     * 
     * MAIN module: we're using the spring-boot dependency injection to create all our instances. 
     * As we're [NOT using @Component], we're scanning our root package and ignoring only the Model objects 
     * //все остальное станет Beans вообще без аннотаций, круто.
     */

//	@Bean
//	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
//		return beanFactory -> {
//			genericApplicationContext((BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
//		};
//	}
//
//	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
//		ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
//		beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
//		//ASK eu: boot is not parent to bean folders...
//		beanDefinitionScanner.scan("eu.getsoftware.cleanarchitecture.adapter", "eu.getsoftware.cleanarchitecture.application", "eu.getsoftware.cleanarchitecture.common");
//	}
//
//	static TypeFilter removeModelAndEntitiesFilter() {
//		return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
//				.getClassName()
//				.endsWith("Model");
//	}
    
}
