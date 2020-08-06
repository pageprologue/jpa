package study.jpa.repository;

import org.springframework.data.repository.RepositoryDefinition;

import study.jpa.domain.Comment;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

    
}