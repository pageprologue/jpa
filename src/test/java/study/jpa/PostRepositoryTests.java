package study.jpa;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import study.jpa.domain.Post;
import study.jpa.repository.PostJpaRepository;

// @DataJpaTest : slicing test. data access layer test
// Only Autowired Repository bean 
// @DataJpaTest ⊃ @Transactional : insert query not executed -> @Rollback(false)


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTests {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PostJpaRepository postJpaRepository;

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

    @Test
    public void savaOrupdate() {
        Post post = new Post();
        post.setTitle("Post 100");
        Post savePost = postJpaRepository.save(post); // persist

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savePost)).isTrue();
        assertThat(savePost == post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("Post 100 Update");
        Post updatedPost = postJpaRepository.save(postUpdate); // merge

        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(postUpdate == updatedPost);

        List<Post> postList = postJpaRepository.findAll();
        assertThat(postList.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        Post post = new Post();
        post.setTitle("Query Method");
        postJpaRepository.save(post);

        List<Post> postList = postJpaRepository.findByTitleStartsWith("Query");
        assertThat(postList.size()).isEqualTo(1);
    }

    @Test
    public void findByTitle() {
        Post post = new Post();
        post.setTitle("Named Query Method");
        postJpaRepository.save(post);

        List<Post> postList = postJpaRepository.findByTitle("Named Query Method", Sort.by("title"));
        List<Post> postListSortByLength = postJpaRepository.findByTitle("Named Query Method", JpaSort.unsafe("LENGTH(title)"));
        assertThat(postList.size()).isEqualTo(1);
        assertThat(postListSortByLength.size()).isEqualTo(1);
    }
}