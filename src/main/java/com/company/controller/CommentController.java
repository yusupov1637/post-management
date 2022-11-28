package com.company.controller;

import com.company.dto.CommentDTO;
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

    @GetMapping("/list/{postId}")
    public ResponseEntity<?> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentList(postId));
    }
}
