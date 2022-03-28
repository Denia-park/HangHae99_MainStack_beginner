package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String reCheckPassword;
}
