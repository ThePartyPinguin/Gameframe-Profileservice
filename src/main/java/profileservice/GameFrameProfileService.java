package profileservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import profileservice.repository.IProfileDao;
import profileservice.repository.IProfileJpaImpl;
import profileservice.repository.ProfileCache;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GameFrameProfileService extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GameFrameProfileService.class);
    }


    public static void main(String[] args){
        SpringApplication.run(GameFrameProfileService.class, args);
    }

    @Bean
    @Primary
    public IProfileDao profileDao(@Autowired IProfileJpaImpl profileJpa){
        ProfileCache cache = new ProfileCache();
        cache.init(profileJpa);
        return cache;
    }
}
