package com.company.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilePostMapper {
    private Long id;
    private String name;
    private String phone;
    private String title;
    private String content;

    public ProfilePostMapper( Long id,String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
