package study.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import study.jpa.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {

    Page<Post> findByTitleContains(String title, Pageable pageable);

    Long countByTitleContains(String title);

    List<Post> findByTitleStartsWith(String Title);

    // Named Query 1.entity class annotation @NamedQuery or 2.infertiave method annotation @Query
    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1")
    List<Post> findByTitle(String title, Sort sort);
}