package study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import study.jpa.domain.Account;

@Transactional
@Component
public class JpaRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("chloe");
        // account.setPassword("password");
        account.setPassword("hibernate");

        // entityManager.persist(account);
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
    }
}