package study.jpa;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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
        Comment comment = new Comment();
        comment.setComment("Study Spring Data JPA");
        comment.setLikeCount(1);
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("jpa", 10);
        assertThat(comments.size()).isEqualTo(0);
    }
}