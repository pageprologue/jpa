package study.jpa.domain;

import lombok.Getter;

@Getter
public class CommentSummary {

    String comment;

    int likeCount;

    int hateCount;

    public CommentSummary(String comment, int likeCount, int hateCount) {
        this.comment = comment;
        this.likeCount = likeCount;
        this.hateCount = hateCount;
    }

    public String getVotes() {
        return this.likeCount + " " + this.hateCount;
    }
}