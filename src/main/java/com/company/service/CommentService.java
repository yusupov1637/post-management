package com.company.service;


import com.company.dto.CommentDTO;
import com.company.dto.PostDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Comment;
import com.company.entity.Post;
import com.company.entity.Profile;
import com.company.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PostService postService;


    public ResponseEntity<?> createComment(CommentDTO commentDTO) {
//        Profile profile = new Profile();
//        profile.setName(profileDTO.getName());
//        profile.setSurname(profile.getSurname());
//        profile.setContact(profileDTO.getContact());
        Comment comment = dtoToEntity(commentDTO);
        commentRepository.save(comment);
        return ResponseEntity.ok().build();
    }

    public Page<Comment> getAllProfile(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Comment> pageObj = commentRepository.findAll(paging);

        List<Comment> list = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<CommentDTO> listdto = new ArrayList<>();
        for (Comment comment : list) {
            listdto.add(entityDto(comment));
        }

        Page<Comment> response = new PageImpl(listdto, paging, totalElements);
        // return new EmployeePageResponseDTO(dtoList, totalElements);
        return response;

    }
    public List<CommentDTO> getCommentList(Long post_id){
        List<Comment> commentListByPostId = commentRepository.getCommentListByPostId(post_id);
        List<CommentDTO> commentDTOList=new ArrayList<>();
        for (Comment comment : commentListByPostId) {
            CommentDTO dto = entityDto(comment);
            commentDTOList.add(dto);
        }
        return commentDTOList;
    }

    public CommentDTO entityDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        ProfileDTO profileDTO = profileService.entityDto(comment.getProfile());
        dto.setProfile(profileDTO);
        PostDTO postDTO = postService.entityDot(comment.getPost());
        dto.setPost(postDTO);
        dto.setCreatedDate(comment.getCreatedDate());
        return dto;
    }

    public Comment dtoToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        Profile profile = profileService.dtoToEntity(commentDTO.getProfile());
        comment.setProfile(profile);
        Post post = postService.dtoToEntity(commentDTO.getPost());
        comment.setPost(post);
        comment.setCreatedDate(LocalDateTime.now());

        return comment;
    }

}
