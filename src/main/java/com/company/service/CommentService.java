package com.company.service;


import com.company.dto.CommentDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Comment;
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


    public ResponseEntity<?> createProfile(CommentDTO commentDTO) {
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

    public CommentDTO entityDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setProfile(comment.getProfile());
        dto.setPost(comment.getPost());
        dto.setCreatedDate(comment.getCreatedDate());
        return dto;
    }

    public Comment dtoToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setProfile(commentDTO.getProfile());
        comment.setPost(commentDTO.getPost());
        comment.setCreatedDate(LocalDateTime.now());

        return comment;
    }

}
