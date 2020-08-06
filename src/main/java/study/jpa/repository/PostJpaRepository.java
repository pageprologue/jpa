package study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.jpa.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    
}