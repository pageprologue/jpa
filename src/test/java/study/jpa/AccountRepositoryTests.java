package study.jpa;

import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import study.jpa.domain.Account;
import study.jpa.domain.QAccount;
import study.jpa.repository.AccountRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void crud() {
        QAccount account = QAccount.account;

        Predicate predicate = account.firstName.containsIgnoreCase("chloe")
                                      .and(account.lastName.startsWith("p"));

        Optional<Account> one = accountRepository.findOne(predicate);
        assertThat(one).isEmpty();

    }
}