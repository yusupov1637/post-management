package com.company.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {
    private Long id;
    @Size(min = 12, max = 12)
    private String phoneNum;

}
