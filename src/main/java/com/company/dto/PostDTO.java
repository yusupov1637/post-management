package com.company.dto;

import com.company.entity.Profile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Profile profile;
}
