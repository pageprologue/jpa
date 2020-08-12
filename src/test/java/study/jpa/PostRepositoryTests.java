package study.jpa;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import study.jpa.domain.Post;
import study.jpa.repository.PostJpaRepository;

// @DataJpaTest : slicing test. data access layer test
// Only Autowired Repository bean 
// @DataJpaTest ⊃ @Transactional : insert query not executed -> @Rollback(false)


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTests {
    
    @Autowired
    PostJpaRepository postJpaRepository;

    @Test
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("Hello, Spring Boot Common");
        assertThat(post.getId()).isNull();
        // When
        Post newPost = postJpaRepository.save(post);
        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postJpaRepository.findAll();
        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> page = postJpaRepository.findAll(PageRequest.of(0, 10));
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        postJpaRepository.findByTitleContains("Spring Data", PageRequest.of(0, 10));
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        long count = postJpaRepository.countByTitleContains("Spring");
        // Then
        assertThat(count).isEqualTo(1l);
    }

    @Test
    public void customRespository() {
        
        Post post = new Post();
        post.setTitle("Hibernate");
        postJpaRepository.save(post);

        // postJpaRepository.findMyPost(); // run insert query
        postJpaRepository.delete(post); // entity status: removed
        postJpaRepository.flush(); // run delete query
    }

}