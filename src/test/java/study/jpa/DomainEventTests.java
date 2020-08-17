package study.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import study.jpa.config.PostRepositoryConfig;
import study.jpa.domain.Post;
import study.jpa.domain.PostPublishedEvent;
import study.jpa.repository.PostSimpleMyRepository;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryConfig.class)
public class DomainEventTests {

    @Autowired
    PostSimpleMyRepository postSimpleMyRepository;
    
    // @Autowired
    // ApplicationContext applicationContext;

    @Test
    public void event() {
        Post post = new Post();
        post.setTitle("event");

        assertThat(postSimpleMyRepository.contains(post)).isFalse();
        postSimpleMyRepository.save(post.publish());
        assertThat(postSimpleMyRepository.contains(post)).isTrue();

        postSimpleMyRepository.delete(post);
        postSimpleMyRepository.flush();
    }
}