package com.alkemy.disney.alkemylab.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class UserBasicDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
