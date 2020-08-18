package study.jpa.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import study.jpa.domain.Post;

public interface PostSimpleMyRepository extends SimpleMyRepository<Post, Long>, QuerydslPredicateExecutor<Post> {
    
}