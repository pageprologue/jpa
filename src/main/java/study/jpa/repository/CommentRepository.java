package study.jpa.repository;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.scheduling.annotation.Async;

import study.jpa.domain.Comment;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable page);
    
    Stream<Comment> findByCommentContainsIgnoreCaseOrderByIdAsc(String keyword);
    
    // @Async: Delegate to a separate thread for execution. (non-recommendation)
    // @Async
    // Future<List<Comment>> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);
}