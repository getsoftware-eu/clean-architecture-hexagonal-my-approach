package eu.getsoftware.onion.cleanarchitecture.usercreation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"eu.getsoftware.onion.cleanarchitecture.usercreation"})
public class OnionMainApplication
{
    public static void main(String[] args) {
        SpringApplication.run(OnionMainApplication.class);
    }
    
}
