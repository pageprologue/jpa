package study.jpa.repository.service;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import study.jpa.repository.CommonJpaRepository;

public class CommonJpaRepositoryService<T, Id extends Serializable> extends SimpleJpaRepository<T, Id> implements CommonJpaRepository<T, Id> {

    private EntityManager entityManager;

    public CommonJpaRepositoryService(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }
    
}