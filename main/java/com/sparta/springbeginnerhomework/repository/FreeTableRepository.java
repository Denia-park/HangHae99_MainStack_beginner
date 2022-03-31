package com.sparta.springbeginnerhomework.repository;

import com.sparta.springbeginnerhomework.model.FreeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeTableRepository extends JpaRepository<FreeTable, Long> {
    List<FreeTable> findAllByOrderByCreatedAtDesc();
}
