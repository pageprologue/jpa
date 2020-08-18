package study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import study.jpa.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>, QuerydslPredicateExecutor<Account> {
    
}