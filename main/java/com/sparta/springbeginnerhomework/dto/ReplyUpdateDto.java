package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;

@Getter

public class ReplyUpdateDto {
    private Long boardNum;
    private Long replyNum;
    private String username;
    private String replyContent;
}
