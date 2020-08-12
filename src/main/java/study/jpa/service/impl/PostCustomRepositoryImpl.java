package study.jpa.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import study.jpa.domain.Post;
import study.jpa.service.PostCustomRepository;

@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository<Post> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        // JPQL
        System.out.println("===== custom findPost query");
        String query = "SELECT p From Post AS p";
        return entityManager.createQuery(query, Post.class).getResultList();
    }

    @Override
    public void delete(Post entity) {
        System.out.println("===== custom delete");
        entityManager.remove(entity);
    }
}