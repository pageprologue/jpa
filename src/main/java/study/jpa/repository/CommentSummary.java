package study.jpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    int getLikeCount();

    int getHateCount();

    // interface open projection
    // @Value("#{target.likeCount + ' ' + target.hateCount}")
    // String getVotes();

    default String getVotes() {
        return getLikeCount() + " " +  getHateCount();
    }
}