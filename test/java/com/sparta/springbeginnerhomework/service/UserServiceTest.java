package com.sparta.springbeginnerhomework.service;

import com.sparta.springbeginnerhomework.dto.SignupRequestDto;
import com.sparta.springbeginnerhomework.dto.UserSignupStatusDto;
import com.sparta.springbeginnerhomework.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원가입 테스트")
class UserServiceTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;

    private Long id ;
    private String username ;
    private String password ;
    private String reCheckpassword ;
    private Long kakaoId;

    @BeforeEach
    void setup(){
        id = 100L;
        username = "gudrl9587";
        password = "q1w2e3r4";
        reCheckpassword = "q1w2e3r4";
        kakaoId = 1234567890L;
    }

    @Test
    @DisplayName("정상 케이스")
    void createUser_Normal(){
        //given
        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username,
                password,
                reCheckpassword
        );

        UserService userService = new UserService(userRepository, passwordEncoder);
        //when
        UserSignupStatusDto userSignupStatusDto = userService.registerUser(signupRequestDto);

        //then
        assertEquals(username,signupRequestDto.getUsername());
        assertEquals(password,signupRequestDto.getPassword());
        assertEquals(reCheckpassword, signupRequestDto.getReCheckPassword());
        assertEquals(reCheckpassword, password);
        assertEquals(signupRequestDto.getReCheckPassword(), signupRequestDto.getPassword());
        assertEquals(0,userSignupStatusDto.getSignupStatus());

        UserSignupStatusDto userSignupStatusDto1 = userService.registerUser(signupRequestDto);

    }

    void  printUserInfo(){
        System.out.println(username + " " + password + " " + reCheckpassword);
    }
}