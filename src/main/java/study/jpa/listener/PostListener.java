package study.jpa.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import study.jpa.domain.PostPublishedEvent;

public class PostListener {

    @EventListener
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("============================");
        System.out.println(event.getPost().getTitle() + "is published!");
        System.out.println("============================");
    }
    
}