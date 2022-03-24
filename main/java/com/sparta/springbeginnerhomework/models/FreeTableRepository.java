package com.sparta.springbeginnerhomework.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeTableRepository extends JpaRepository<FreeTable, Long> {
    List<FreeTable> findAllByOrderByCreatedAtDesc();
}
