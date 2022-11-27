package com.company.dto;

import com.company.entity.Post;
import com.company.entity.Profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Long id;
    private String content;
    private Post post;
    private Profile profile;
    private LocalDateTime createdDate;
}
