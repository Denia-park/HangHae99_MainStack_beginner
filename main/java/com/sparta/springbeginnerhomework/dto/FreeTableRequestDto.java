package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class FreeTableRequestDto {
    private String title;
    private String writer;
    private String content;
}
