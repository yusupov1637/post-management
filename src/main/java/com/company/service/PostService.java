package com.company.service;

import com.company.dto.PostDTO;
import com.company.entity.Post;
import com.company.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostService {
    @Autowired
    PostRepository postRepository;

    public ResponseEntity<?> createPost(PostDTO postDTO) {
        Post post = dtoToEntity(postDTO);
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }

    public Page<Post> getAllProfile(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Post> pageObj = postRepository.findAll(paging);

        List<Post> list = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<PostDTO> listdto = new ArrayList<>();
        for (Post post : list) {
            listdto.add(entityDot(post));
        }

        Page<Post> response = new PageImpl(listdto, paging, totalElements);
        // return new EmployeePageResponseDTO(dtoList, totalElements);
        return response;

    }

    public Post dtoToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setProfile(postDTO.getProfile());
        post.setCreatedDate(LocalDateTime.now());
        return post;
    }

    public PostDTO entityDot(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setTitle(post.getTitle());
        postDTO.setProfile(post.getProfile());
        postDTO.setCreatedDate(post.getCreatedDate());
        return postDTO;
    }
}
