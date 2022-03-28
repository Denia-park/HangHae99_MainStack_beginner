package com.sparta.springbeginnerhomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrCodeObject {
    private final int errCode;
    private final String errCodeMsg;
}
