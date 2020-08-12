package study.jpa.service;

import java.util.List;

import study.jpa.domain.Post;

public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    void delete(T entity);
    
}