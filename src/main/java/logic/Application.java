package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * Created by V3790147 on 5/16/2016.
 */

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    LogRepository logRepository;


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Override
    public void run(String... args) throws Exception{
        LogEntity logEntity = new LogEntity("128.0.0.1", "user-identifier", "frank", "[10/Oct/2000:13:55:36 -0700]", "GET /apache_pb.gif HTTP/1.0","200", "345");
        logRepository.save(logEntity);
    }


}