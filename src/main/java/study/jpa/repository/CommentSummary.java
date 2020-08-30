package study.jpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    Boolean getReply();

    Boolean getBest();

}