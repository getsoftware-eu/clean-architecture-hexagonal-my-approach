package eu.getsoftware.cleanarchitecture.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"eu.getsoftware.onion.cleanarchitecture"})
public class MainApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }
    
}
