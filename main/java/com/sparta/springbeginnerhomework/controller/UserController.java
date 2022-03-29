package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.dto.UserSignupStatusDto;
import com.sparta.springbeginnerhomework.dto.SignupRequestDto;
import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import com.sparta.springbeginnerhomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    private final KakaoUserService kakaoUserService;
//
//    @Autowired
//    public UserController(UserService userService, KakaoUserService kakaoUserService) {
//        this.userService = userService;
//        this.kakaoUserService = kakaoUserService;
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            return "redirect:/?loginUserDoNotEnterErr"; //
        }

        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            return "redirect:/?loginUserDoNotEnterErr"; //
        }

        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto , Model model) {
        UserSignupStatusDto userSignupStatusDto = userService.registerUser(requestDto);

        if(userSignupStatusDto.isSignupStatusNoERR()) {
            return "login";
        }

        model.addAttribute("signupStatus",userSignupStatusDto.getSignupStatus());
        model.addAttribute("signupStatusErrMsg",userSignupStatusDto.getSignupStatusErrMsg());
        return "signup";
    }

//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}