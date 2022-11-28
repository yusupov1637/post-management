package com.company.controller;

import com.company.dto.CommentDTO;
import com.company.dto.ProfileDTO;
import com.company.mapper.ProfileCommentMapper;
import com.company.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list_post/{postId}")
    public ResponseEntity<?> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentList(postId));
    }
    @GetMapping("/list_profile/{profileId}")
    public ResponseEntity<?> getByProfileId(@PathVariable("profileId") Long profileId) {
        return ResponseEntity.ok(commentService.getCommentListByProfileId(profileId));
    }

    @GetMapping("/list_profile_comment/{prId}")
    public List<ProfileCommentMapper> getPCList(@PathVariable("prId") Long prId){
       return commentService.getProfileAndComment(prId);
    }

    @GetMapping("/profile_by_comment/{cId}")
    public ProfileDTO getprofile(@PathVariable("cId") Long cId){
        ProfileDTO profile = commentService.getProfile(cId);
        return profile;
    }

    @GetMapping("/count_by_postid/{postId}")
    public Integer getCount(@PathVariable("postId") Long postId){
        Integer count = commentService.getCount(postId);
        return count;
    }
    @GetMapping("/get_lastcomment_by_postid/{postId}")
    public CommentDTO get1Com(@PathVariable("postId") Long postId){
       return commentService.getLast1(postId);
    }

}
