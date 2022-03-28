package com.sparta.springbeginnerhomework.service;

import com.sparta.springbeginnerhomework.dto.UserSignupStatusDto;
import com.sparta.springbeginnerhomework.dto.SignupRequestDto;
import com.sparta.springbeginnerhomework.model.User;
import com.sparta.springbeginnerhomework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final int NO_ERR = 0;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserSignupStatusDto registerUser(SignupRequestDto requestDto) {
        UserSignupStatusDto userSignupStatusDto = checkSignupRequestDto(requestDto);

        if(userSignupStatusDto.getSignupStatus() == NO_ERR){
            // 패스워드 암호화
            String encodePassword = passwordEncoder.encode(requestDto.getPassword());
            User user = new User(requestDto.getUsername(), encodePassword);
            userRepository.save(user);
        }

        return userSignupStatusDto;
    }

    private UserSignupStatusDto checkSignupRequestDto(SignupRequestDto requestDto) {
        final int USERNAME_FORM_ERR = -1;
        final int PASSWORD_FORM_ERR = -2;
        final int RE_CHECK_PASSWORD_FORM_ERR = -3;
        final int DUPLICATE_USERNAME_ERR = -4;

        int errCode = NO_ERR;
        String errCodeMsg = null;

        //1. username 정규식 확인
        String idRegex = "^[A-Za-z0-9]{3,30}$";
        String pwRegex = "^[A-Za-z0-9!@#$%^&*+=]{4,30}$";

        if (!Pattern.matches(idRegex, requestDto.getUsername())){
            errCode = USERNAME_FORM_ERR;
            errCodeMsg = "1.아이디 조건을 확인해주세요.";
        }
        //2. PW 와 check_PW 확인
        else if (!requestDto.getPassword().equals(requestDto.getReCheckPassword())){
            errCode = RE_CHECK_PASSWORD_FORM_ERR;
            errCodeMsg = "3. 비밀번호 재확인 조건을 확인해주세요.";
        }
        //3. PW 정규식 확인 && PW 에 username 가 포함되었는지 확인
        else if ( !Pattern.matches(pwRegex, requestDto.getPassword()) || requestDto.getPassword().contains(requestDto.getUsername()) ){
            errCode = PASSWORD_FORM_ERR;
            errCodeMsg = "2.비밀번호 조건을 확인해주세요.";
        }else{
            // 회원 ID 중복 확인
            String username = requestDto.getUsername();
            Optional<User> found = userRepository.findByUsername(username);
            if (found.isPresent()) {
                errCode = DUPLICATE_USERNAME_ERR;
                errCodeMsg = "중복된 사용자 ID 가 존재합니다.";
            }
        }

        return new UserSignupStatusDto(errCode,errCodeMsg);
    }

}