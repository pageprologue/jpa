package study.jpa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
// @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post AS p WHERE p.title = ?1")
public class Post extends AbstractAggregateRoot<Post>{

    @Id
    @GeneratedValue
    private Long id;
    
    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public void addCommnet(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
    
    public Post publish() {
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}