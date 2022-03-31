package com.sparta.springbeginnerhomework.model;

import com.sparta.springbeginnerhomework.dto.UserSignupStatusDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("정상 케이스")
    void createUser_Normal() {
        //given
        Long id =100L;
        String username = "형기";
        String password = "1234!!";
        Long kakaoId = null;

        //when
        User user = new User(username,password);

        //then
        assertNull(user.getKakaoId());
        assertEquals(username,user.getUsername());
        assertEquals(password,user.getPassword());

    }

}