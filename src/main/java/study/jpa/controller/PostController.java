package study.jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import study.jpa.domain.Post;
import study.jpa.repository.PostJpaRepository;
import study.jpa.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PostController {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @GetMapping(value="/posts/{id}")
    public String getMethodName(@PathVariable Long id) {
        Optional<Post> byId = postJpaRepository.findById(id);
        Post post = byId.get();
        return post.getTitle();
    }
}