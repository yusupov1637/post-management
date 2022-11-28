package com.company.repository;

import com.company.entity.Comment;

import com.company.entity.Profile;
import com.company.mapper.ProfileCommentMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select c.* from Comment c inner join Post p on p.id=c.post_id where p.id=:pId", nativeQuery = true)
    List<Comment> getCommentListByPostId(@Param("pId") Long id);

    @Query(value = "select c.* from Comment c inner join Profile p on p.id=c.profile_id where p.id=:pId", nativeQuery = true)
    List<Comment> getCommentListByProfileId(@Param("pId") Long id);

    @Query("select new com.company.mapper.ProfileCommentMapper(c.id,c.content,p.id,p.name) from Comment c inner join Profile p where p.id=:pId")
    List<ProfileCommentMapper> getProfileAndComment(@Param("pId") Long pid);

    Integer countByPostId(Long post_id);

    @Query(value = "select c.* from Comment c inner join  Post ps on c.post_id = ps.id where ps.id=:pId order by c.created_date DESC limit 1", nativeQuery = true)
    Comment findlast1Comment(@Param("pId") Long pId);

}
