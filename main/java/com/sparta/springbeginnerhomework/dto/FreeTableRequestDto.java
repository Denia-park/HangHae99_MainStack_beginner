package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class FreeTableRequestDto {
    private String title;
    private String username;
    private String content;
}
