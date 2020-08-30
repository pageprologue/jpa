package study.jpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    Boolean getReply();

    Boolean getBest();

    // interface open projection
    @Value("#{target.likeCount + ' ' + target.hateCount}")
    String getVotes();
}