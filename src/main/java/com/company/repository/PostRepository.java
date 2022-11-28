package com.company.repository;

import com.company.entity.Post;


import com.company.entity.Profile;
import com.company.mapper.ProfilePostMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select new com.company.mapper.ProfilePostMapper(pr.id,pr.name,pr.surname,ps.content,ps.title) from Profile pr inner join Post ps where pr.id=:prId")
    List<ProfilePostMapper> getProfileAndPost(@Param("prId")Long id);

    @Query("select new com.company.mapper.ProfilePostMapper(pr.id,pr.name,ps.title) from Profile pr inner join Post ps where pr.id=:prId")
    List<ProfilePostMapper> getPNameTitle(@Param("prId")Long id);

    @Query("select Post (p.title,p.createdDate) from Post  p where p.id=:id")
    Post getPostById(@Param("id")Long id);

//    @Query(value = "select p.* from Profile p inner join  Post ps on ps.profile_id = p.id where p.id=:pId order by ps.created_date DESC limit 5", nativeQuery = true)
//    List<Profile> findlast5PostsProfile(@Param("pId") Long pId);

//    @Query(value = " SELECT pr.* from profile pr join post p on pr.id = p.profile_id where p.id=:id", nativeQuery = true)
//    Profile getProfileByPost_id(@Param("id") Integer id);

    @Query(value = "select count(*) from Post p where p.profile_id=?1",nativeQuery = true)
    Integer getCount(Long id);

    List<Post> findByCreatedDate(LocalDate createdDate);

/*
    @Query(value = "FROM Post p where p.=:createdDate")
    List<Post> findPostByCreatedDateToday(@Param("createdDate") LocalDate today);*/
}
