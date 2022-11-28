package com.company.repository;

import com.company.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "select c.* from Comment c inner join Post p on p.id=c.post_id where p.id=:pId",nativeQuery = true)
    List<Comment> getCommentListByPostId(@Param("pId") Long id);

}
