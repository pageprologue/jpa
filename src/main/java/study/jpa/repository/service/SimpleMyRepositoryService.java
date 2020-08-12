package study.jpa.repository.service;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import study.jpa.repository.SimpleMyRepository;


public class SimpleMyRepositoryService<T, Id extends Serializable> extends SimpleJpaRepository<T, Id> implements SimpleMyRepository<T, Id> {

    private EntityManager entityManager;

    public SimpleMyRepositoryService(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }
    
}