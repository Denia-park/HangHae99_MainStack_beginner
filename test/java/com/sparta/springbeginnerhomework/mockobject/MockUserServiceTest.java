package com.sparta.springbeginnerhomework.mockobject;


import com.sparta.springbeginnerhomework.dto.SignupRequestDto;
import com.sparta.springbeginnerhomework.dto.UserSignupStatusDto;
import com.sparta.springbeginnerhomework.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("회원 가입 테스트")
public class MockUserServiceTest {
    
    @Nested
    @DisplayName("회원가입시 조건 검사")
    class CreatedSignupUser{
        private Long id ;
        private String username ;
        private String password ;
        private String reCheckpassword ;
        private Long kakaoId;

        MockUserService mockUserService = new MockUserService();

        @BeforeEach
        void setup(){
            id = 100L;
            username = "gudrl9587";
            password = "q1w2e3r4";
            reCheckpassword = "q1w2e3r4";
            kakaoId = 1234567890L;
        }
        
        @Nested
        @DisplayName("정상 케이스")
        class SucessCases{
            @Test
            @DisplayName("정상 케이스1")
            void updateUser_Normal1() {
                // given

                SignupRequestDto signupRequestDto = new SignupRequestDto(
                        username,
                        password,
                        reCheckpassword
                );

                // when
                UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                // then
                assertEquals(0, userSignupStatusDto.getSignupStatus());
            }

            @Test
            @DisplayName("정상 케이스 2")
            void updateUser_Normal2() {
                // given
                MockUserService mockUserService = new MockUserService();

                username = "sugarboy";
                password = "123456789";
                reCheckpassword = "123456789";

                SignupRequestDto signupRequestDto = new SignupRequestDto(
                        username,
                        password,
                        reCheckpassword
                );

                // when
                UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                // then
                assertEquals(0, userSignupStatusDto.getSignupStatus());
            }
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases{

            @Nested
            @DisplayName("ID : errCode -1")
            class Id {
                final int ID_ERR_CODE = -1;

                @Test
                @DisplayName("빈 문자열 : Null 아님")
                void fail1() {
                    // given
                    username = "";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("3자리 미만")
                void fail2() {
                    // given
                    username = "ab";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("특수문자")
                void fail3() {
                    // given
                    username = "abcdef!!@#132";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("너무 긴 ID")
                void fail4() {
                    // given
                    username = "abcdefACDAQRakldk1adfmvkfnjd132amandkdnsjrudndjs";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

            }

            @Nested
            @DisplayName("PW : errCode -2")
            class Pw{
                final int PW_ERR_CODE = -2;

                @Test
                @DisplayName("빈 문자열 : Null 아님")
                void fail1() {
                    // given
                    password = reCheckpassword = "";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("4자리 미만")
                void fail2() {
                    // given
                    password = reCheckpassword = "ab";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("특수문자")
                void fail3() {
                    // given
                    password = reCheckpassword = "abcdef!!@#132{}";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("너무 긴 ID")
                void fail4() {
                    // given
                    password = reCheckpassword = "abcdefACDAQRakldk1adfmvkfnjd132amandkdnsjrudndjs";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("ID 내용 포함")
                void fail5() {
                    // given
                    password = reCheckpassword = "gudrl9587123456!!@";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

            }

            @Nested
            @DisplayName("PW ReCheck : errCode -3")
            class PwReCheck{

                final int PW_RECHECK_ERR_CODE = -3;
                @Test
                @DisplayName("비밀번호 확인 실패1")
                void fail1() {
                    // given
                    reCheckpassword = "11123477";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_RECHECK_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("비밀번호 확인 실패2")
                void fail2() {
                    // given
                    reCheckpassword = "ab1c54d8q";

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(PW_RECHECK_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

            }

            @Nested
            @DisplayName("ID 중복검사 : errCode -4")
            class DuplicateID{
                final int ID_RECHECK_ERR_CODE = -4;

                @Test
                @DisplayName("ID 중복 검사 실패1")
                void fail1() {
                    // given
                    username = "gudrl1234";
                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    mockUserService.registerUser(signupRequestDto);
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_RECHECK_ERR_CODE, userSignupStatusDto.getSignupStatus());
                    assertEquals("중복된 사용자 ID 가 존재합니다.", userSignupStatusDto.getSignupStatusErrMsg());
                }

                @Test
                @DisplayName("ID 중복 검사 실패2")
                void fail2() {
                    // given
                    username = "sugarsugar";
                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    mockUserService.registerUser(signupRequestDto);
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(ID_RECHECK_ERR_CODE, userSignupStatusDto.getSignupStatus());
                    assertEquals("중복된 사용자 ID 가 존재합니다.", userSignupStatusDto.getSignupStatusErrMsg());
                }

            }

            @Nested
            @DisplayName("Null 입력 발생 : errCode -5")
            class Null{
                final int NULL_ERR_CODE = -5;

                @Test
                @DisplayName("Null : ID")
                void fail1() {
                    // given
                    username = null;

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(NULL_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("Null : PW")
                void fail2() {
                    // given
                    password = null;

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(NULL_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }

                @Test
                @DisplayName("Null : reCheckPW")
                void fail3() {
                    // given
                    reCheckpassword = null;

                    SignupRequestDto signupRequestDto = new SignupRequestDto(
                            username,
                            password,
                            reCheckpassword
                    );

                    // when
                    UserSignupStatusDto userSignupStatusDto = mockUserService.registerUser(signupRequestDto);

                    // then
                    assertEquals(NULL_ERR_CODE, userSignupStatusDto.getSignupStatus());
                }
            }



        }
    }

    
//    @Test
//    @DisplayName("관심 상품 희망가 - 최저가 미만으로 변경")
//    void updateUser_Failed() {
//        // given
//        int myprice = MIN_MY_PRICE - 50;
//
//        UserMypriceRequestDto requestMyPriceDto = new UserMypriceRequestDto(
//                myprice
//        );
//
//        Long userId = 777L;
//        UserRequestDto  requestUserDto = new UserRequestDto(
//                "오리온 꼬북칩 초코츄러스맛 160g",
//                "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg",
//                "https://search.shopping.naver.com/gate.nhn?id=24161228524",
//                2350
//        );
//
//        MockUserService mockUserService = new MockUserService();
//        // 회원의 관심상품을 생성
//        User product = mockUserService.createUser(requestUserDto, userId);
//
//        // when
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            mockUserService.updateUser(product.getId(), requestMyPriceDto);
//        });
//
//        // then
//        assertEquals(
//                "유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.",
//                exception.getMessage()
//        );
//    }
}