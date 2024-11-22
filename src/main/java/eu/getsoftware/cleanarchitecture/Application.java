package eu.getsoftware.cleanarchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//eu: problem MainApplication класс (@SpringBootApplication) находится не в родительском пакете по отношению к Beans!!.
@SpringBootApplication//(scanBasePackages = {"eu.getsoftware.cleanarchitecture"})
public class Application
{
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    
}
