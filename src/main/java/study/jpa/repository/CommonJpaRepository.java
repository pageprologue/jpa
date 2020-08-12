package study.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonJpaRepository<T, Id extends Serializable> extends JpaRepository<T, Id> {
    
    boolean contains(T entity);
    
}