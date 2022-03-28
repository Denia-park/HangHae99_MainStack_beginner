package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserSignupStatusDto {
    private final int signupStatus;
    private final String signupStatusErrMsg;

    public boolean isSignupStatusNoERR(){
        return this.signupStatus == 0;
    }
}
