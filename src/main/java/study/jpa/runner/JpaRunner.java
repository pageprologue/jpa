package study.jpa.runner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import study.jpa.config.JPARegistrar;
import study.jpa.domain.Account;
import study.jpa.domain.Comment;
import study.jpa.domain.JPA;
import study.jpa.domain.Post;
import study.jpa.domain.Study;
import study.jpa.repository.PostJpaRepository;

@Transactional
@Component
public class JpaRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PostJpaRepository postJpaRepository;

    @Autowired
    JPA jap;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // accountSave();

        // postSave();

        // fetchSelect();

        // queryMapping();

        // jpaRepository();

        jpaRegistrar();
    }

    /**
     * EntityManager session save 
     */
    public void accountSave() {
        Account account = new Account();
        account.setUsername("chloe");
        account.setPassword("password");

        Study study = new Study();
        study.setName("Spring Data JPA");
        account.addStudy(study);

        // entityManager.persist(account);
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account chloe = session.load(Account.class, account.getId());
        System.out.println("### Persistence Context Cache: " + chloe.getUsername());
    }

    /**
     * Casecade save, delete
     */
    public void postSave() {
        Post post = new Post();
        post.setTitle("Spring Data JPA Stuty");
        
        Comment comment1 = new Comment();
        comment1.setComment("hello");
        post.addCommnet(comment1);
        
        Comment comment2 = new Comment();
        comment2.setComment("nice to meet you");
        post.addCommnet(comment2);
        
        Session session = entityManager.unwrap(Session.class);
        session.save(post);
        // session.delete(post);
    }

    /**
     * Fetch select
     */
    public void fetchSelect() {
        Session session = entityManager.unwrap(Session.class);
        Comment comment = session.get(Comment.class, 5l);
        System.out.println(comment.getComment());
        System.out.println( comment.getPost().getTitle());
    }

    public void queryMapping() {
        List<Post> posts =  new ArrayList<>();

        // 1. TypedQuery
        TypedQuery<Post> typedQuery = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        posts = typedQuery.getResultList();
        posts.forEach(System.out::println);

        // 2. CriteriaBuilder
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);

        posts = entityManager.createQuery(criteriaQuery).getResultList();
        posts.forEach(System.out::println);

        // 3. createNamedQuery / createNativeQuery
        posts = entityManager.createNativeQuery("SELECT * FROM Post", Post.class).getResultList();
        posts.forEach(System.out::println);
    }

    /**
     * JpaRepository<Entity, Id>
     * @Repository가 없어도 빈으로 등록해 줌
     */
    public void jpaRepository() {
        postJpaRepository.findAll().forEach(System.out::println);
    }

    /**
     * ImportBeanDefinitionRegistrar
     */
    public void jpaRegistrar() {
        System.out.println(jap.getName());
    }
}