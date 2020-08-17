package study.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.jpa.listener.PostListener;

@Configuration
public class PostRepositoryConfig {
    
    @Bean
    public PostListener postListener() {
        return new PostListener();
    }
    
}