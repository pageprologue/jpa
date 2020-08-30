package study.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private Integer likeCount = 0;

    @ManyToOne(fetch = FetchType.EAGER) // @_One : Default fetchType is EAGER
    private Post post;
}