package study.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
// @NamedEntityGraph(name = "Comment.post", attributeNodes = @NamedAttributeNode("post"))
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private String CommentType;
    
    private boolean reply;

    private boolean best;

    private int likeCount = 0;

    private int hateCount = 0;

    @ManyToOne(fetch = FetchType.LAZY) // @_One : Default fetchType is EAGER
    private Post post;
}