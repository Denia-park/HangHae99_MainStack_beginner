package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.dto.ErrCodeObject;
import com.sparta.springbeginnerhomework.dto.SignupRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Pattern;

@Controller
public class UserController {

//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    private final UserService userService;
//    private final KakaoUserService kakaoUserService;
//
//    @Autowired
//    public UserController(UserService userService, KakaoUserService kakaoUserService) {
//        this.userService = userService;
//        this.kakaoUserService = kakaoUserService;
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto , Model model) {
        ErrCodeObject errCodeObject = getSignupErrCode(requestDto);

        if(errCodeObject.getErrCode() == 0) {
            userService.registerUser(requestDto);
            return "login";
        }
        model.addAttribute("errCode",errCodeObject.getErrCode());
        model.addAttribute("errCodeMsg",errCodeObject.getErrCodeMsg());
        return "signup";
    }

    private ErrCodeObject getSignupErrCode(SignupRequestDto requestDto) {
        final int NO_ERR = 0;
        final int USERNAME_ERR = 1;
        final int PASSWORD_ERR = 2;
        final int RE_CHECK_PASSWORD_ERR = 3;

        int errCode = NO_ERR;
        String errCodeMsg = null;

        //1. username 정규식 확인
        String idRegex = "^[A-Za-z0-9]{3,30}$";
        String pwRegex = "^[A-Za-z0-9!@#$%^&*+=]{4,30}$";

        if (!Pattern.matches(idRegex, requestDto.getUsername())){
            errCode = USERNAME_ERR;
            errCodeMsg = "1.Username 조건을 확인해주세요.";
        }
        //2. PW 와 check_PW 확인
        else if (!requestDto.getPassword().equals(requestDto.getReCheckPassword())){
            errCode = RE_CHECK_PASSWORD_ERR;
            errCodeMsg = "3. 비밀번호 확인 조건을 확인해주세요.";
        }
        //3. PW 정규식 확인 && PW 에 username 가 포함되었는지 확인
        else if ( !Pattern.matches(pwRegex, requestDto.getPassword()) || requestDto.getPassword().contains(requestDto.getUsername()) ){
            errCode = PASSWORD_ERR;
            errCodeMsg = "2.PW 조건을 확인해주세요.";
        }

        return new ErrCodeObject(errCode,errCodeMsg);
    }

//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}