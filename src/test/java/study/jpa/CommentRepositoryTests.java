package study.jpa;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import study.jpa.domain.Comment;
import study.jpa.repository.CommentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTests {
    @Autowired
    CommentRepository commentRepository;

    @Ignore
    public void crud() {
        // Given
        Comment comment = new Comment();
        comment.setComment("First Comment");
        commentRepository.save(comment);

        // When
        List<Comment> comments = commentRepository.findAll();
        // Then
        assertThat(comments.size()).isEqualTo(1);

        // When
        long count = commentRepository.count();
        // The4
        assertThat(count).isEqualTo(1);

        // When
        Optional<Comment> commentById = commentRepository.findById(1l);
        // Then
        // assertThat(commentById).isEmpty();
        assertThat(commentById).isPresent();
        commentById.orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void queryExample() {
        // Given
        this.createComment("Study Spring Data JPA", 1);
        // When
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("jpa", 10);
        // Then
        assertThat(comments.size()).isEqualTo(0);

        // Given
        this.createComment("Jpa", 100);
        this.createComment("Jpa", 20);
        this.createComment("Jpa", 50);
        // When
        List<Comment> orderByComments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("jpa");
        // Then
        assertThat(orderByComments).first().hasFieldOrPropertyWithValue("likeCount", 100);

        /* PageRequest Page List */
        // When
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "likeCount"));
        Page<Comment> pageComments = commentRepository.findByCommentContainsIgnoreCase("jpa", pageRequest);
        // Then
        assertThat(pageComments.getTotalElements()).isEqualTo(4);
        assertThat(pageComments).first().hasFieldOrPropertyWithValue("likeCount", 1);

        /* PageRequest Page List */
        try(Stream<Comment> streamComments = commentRepository.findByCommentContainsIgnoreCaseOrderByIdAsc("jpa")) {
            Comment firstComment = streamComments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(1);
        }
    }

    private void createComment(String keyword, int likeCount) {
        Comment newComment = new Comment();
        newComment.setComment(keyword);
        newComment.setLikeCount(likeCount);
        commentRepository.save(newComment);
    }
}