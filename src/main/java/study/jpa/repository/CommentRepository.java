package study.jpa.repository;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;

import study.jpa.domain.Comment;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);
    
}