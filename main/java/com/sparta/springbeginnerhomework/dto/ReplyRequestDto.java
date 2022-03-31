package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class ReplyRequestDto {

    private Long boardNum;
    private Long replyNum;
    private String username;
    private String replyContent;
}
