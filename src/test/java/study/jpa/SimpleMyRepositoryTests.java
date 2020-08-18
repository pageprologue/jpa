package study.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import study.jpa.domain.Post;
import study.jpa.domain.QPost;
import study.jpa.repository.PostSimpleMyRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.querydsl.core.types.Predicate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SimpleMyRepositoryTests {

    @Autowired
    PostSimpleMyRepository postSimpleMyRepository;

    @Test
    public void customRespository() {
        
        Post post = new Post();
        post.setTitle("Hibernate");

        assertThat(postSimpleMyRepository.contains(post)).isFalse();
        postSimpleMyRepository.save(post);
        assertThat(postSimpleMyRepository.contains(post)).isTrue();

        // postJpaRepository.findMyPost(); // run insert query
        postSimpleMyRepository.delete(post); // entity status: removed
        postSimpleMyRepository.flush(); // run delete query
    }

    @Test
    public void querydsl() {
        Post post = new Post();
        post.setTitle("Hibernate");
        Post newPost = postSimpleMyRepository.save(post);

        assertTrue(postSimpleMyRepository.contains(newPost));

        Optional<Post> one = postSimpleMyRepository.findOne(QPost.post.title.containsIgnoreCase("Hi"));
        assertTrue(one.isPresent());
    }
    
}