package study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import study.jpa.domain.Account;
import study.jpa.domain.Comment;
import study.jpa.domain.Post;
import study.jpa.domain.Study;

@Transactional
@Component
public class JpaRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //* entityManager session save *//
        // accountSave();

        //* Casecade *//
        // postSave();

        //* Fetch *//
        fetchSelect();
    }

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

    public void fetchSelect() {
        Session session = entityManager.unwrap(Session.class);
        Comment comment = session.get(Comment.class, 5l);
        System.out.println(comment.getComment());
        System.out.println( comment.getPost().getTitle());
    }
}