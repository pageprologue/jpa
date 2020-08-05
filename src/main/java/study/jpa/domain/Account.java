package study.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;
    
}