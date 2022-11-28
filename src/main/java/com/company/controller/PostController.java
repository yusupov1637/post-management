package com.company.controller;

import com.company.dto.PostDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Post;
import com.company.entity.Profile;
import com.company.mapper.ProfilePostMapper;
import com.company.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Post>> getList(@RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        return ResponseEntity.ok(postService.getAllPost(page, size));
    }
    @GetMapping("/profile_id/{id}")
    public List<ProfilePostMapper> getPOstAndProfileList(@PathVariable Long profile_id) {
        List<ProfilePostMapper> list = postService.getList(profile_id);
        return list;
    }
    @GetMapping("/profile_name_title/{id}")
    public List<ProfilePostMapper> getProfileNAMePostTItle(@PathVariable("id") Long profile_id) {
        List<ProfilePostMapper> list = postService.getListNAmeTitle(profile_id);
        return list;
    }

    @GetMapping("/post-title-created/{id}")
    public PostDTO getTitleDAte(@PathVariable Long post_id) {
        PostDTO titleCreated = postService.getTitleCreated(post_id);
        return titleCreated;
    }
    @GetMapping("/last5_postsprofile/{id}")
    public List<Profile> get5LAst(@PathVariable("id") Long profile_id) {
        List<Profile> lastProfile = postService.get5LastProfile(profile_id);
        return lastProfile;
    }
    @GetMapping("/countprofilepost/{id}")
    public ResponseEntity<?> getCount(@PathVariable("id") Long profile_id) {
        Integer countPostProfile = postService.getCountPostProfile(profile_id);
        return ResponseEntity.ok(countPostProfile);

    }

}
