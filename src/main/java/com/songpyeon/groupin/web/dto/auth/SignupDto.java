package com.songpyeon.groupin.web.dto.auth;

import com.songpyeon.groupin.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {
    @Size(min=2, max=20)
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private int region;


    public User toEntity(){
        return User.builder()
                .id(id)
                .password(password)
                .email(email)
                .nickname(nickname)
                .region(region)
                .build();


    }
}
