package study.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import study.jpa.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {

    Page<Post> findByTitleContains(String title, Pageable pageable);

    Long countByTitleContains(String title);

    List<Post> findByTitleStartsWith(String Title);

    // Named Query 1.entity class annotation @NamedQuery or 2.infertiave method annotation @Query
    @Query(value = "SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String title, Sort sort);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Post p SET p.title = ?2 WHERE p.id = ?1")
    int updateTitle(Long id, String title);
    
}