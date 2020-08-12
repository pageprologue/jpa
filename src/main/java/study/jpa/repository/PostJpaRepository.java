package study.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import study.jpa.domain.Post;
import study.jpa.service.PostCustomRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {

    Page<Post> findByTitleContains(String title, Pageable pageable);

    Long countByTitleContains(String title);
}