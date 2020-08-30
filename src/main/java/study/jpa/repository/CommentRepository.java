package study.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.scheduling.annotation.Async;

import study.jpa.domain.Comment;
import study.jpa.repository.CommonRepository;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable page);
    
    Stream<Comment> findByCommentContainsIgnoreCaseOrderByIdAsc(String keyword);
    
    // @Async: Delegate to a separate thread for execution. (non-recommendation)
    // @Async
    // Future<List<Comment>> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    // EntityGraphType.LOAD is follows the basic strategy of FETCH TYPE mode.
    // EntityGraphType.FETCH sets the value set for @NamedEntityGraph to FETCH mode and the rest to EAGER mode
    // Even so, the default value (ex. id, comment) is set in EAGER mode.
    // @EntityGraph(value = "Comment.post", type = EntityGraphType.LOAD) 
    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);
}