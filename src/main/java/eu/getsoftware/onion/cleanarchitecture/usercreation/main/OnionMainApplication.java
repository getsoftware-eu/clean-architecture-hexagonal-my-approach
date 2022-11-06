package eu.getsoftware.onion.cleanarchitecture.usercreation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import eu.getsoftware.onion.cleanarchitecture.usercreation.main.config.InfrastructureConfiguration;

@SpringBootApplication(scanBasePackages = {"eu.getsoftware.onion.cleanarchitecture.usercreation.application", "eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure"})
@Import({InfrastructureConfiguration.class})
public class OnionMainApplication
{
    public static void main(String[] args) {
        SpringApplication.run(OnionMainApplication.class);
    }
    
}
