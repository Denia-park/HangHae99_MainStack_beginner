package com.sparta.springbeginnerhomework.models;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class FreeTableRequestDto {
    private String title;
    private String writer;
    private String content;
}
