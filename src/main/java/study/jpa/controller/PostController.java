package study.jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import study.jpa.domain.Post;
import study.jpa.repository.PostJpaRepository;
import study.jpa.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PostController {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @GetMapping(value="/posts/{id}")
    public String getMethodName(@PathVariable("id") Post post) {
        /* 
        // DomainClassConverter does exactly the same job as findById.
        Optional<Post> byId = postJpaRepository.findById(id);
        Post post = byId.get(); 
         */

        return post.getTitle();
    }

    @GetMapping(value="/posts")
    public Page<Post> getPosts(Pageable pageable) {

        return postJpaRepository.findAll(pageable);
    }

    @GetMapping(value="/posts/hateoas")
    public PagedModel<EntityModel<Post>> getPostsHateoas(Pageable pageable, PagedResourcesAssembler<Post> assembler) {

        return assembler.toModel(postJpaRepository.findAll(pageable));
    }
    
}