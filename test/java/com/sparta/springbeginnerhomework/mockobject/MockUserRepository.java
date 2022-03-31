package com.sparta.springbeginnerhomework.mockobject;

import com.sparta.springbeginnerhomework.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepository {

    private List<User> users = new ArrayList<>();
    // 유저 테이블 ID: 1부터 시작
    private Long id = 1L;

    // 유저 저장
    public User save(User user) {
        // 신규 유저 -> DB 에 저장
        user.setId(id);
        ++id;
        users.add(user);
        return user;
    }

    // 유저 ID 로 유저 조회
    public Optional<User> findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    // 회원 ID 로 등록된 유저 조회
    public List<User> findAllByUserId(Long id) {
        List<User> userProducts = new ArrayList<>();
        for (User user : users) {
            if (user.getId().equals(id)) {
                userProducts.add(user);
            }
        }

        return userProducts;
    }

    // 유저 전체 조회
    public List<User> findAll() {
        return users;
    }
}