package study.jpa.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.jpa.domain.PostPublishedEvent;

@Configuration
public class PostRepositoryConfig {
    
    @Bean
    public ApplicationListener<PostPublishedEvent> postListener() {
        return event -> {
                System.out.println("============================");
                System.out.println(event.getPost().getTitle() + "is published!");
                System.out.println("============================");
        };
    }
}