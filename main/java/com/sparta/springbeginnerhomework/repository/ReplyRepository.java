package com.sparta.springbeginnerhomework.repository;

import com.sparta.springbeginnerhomework.model.FreeTable;
import com.sparta.springbeginnerhomework.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findALLByBoardNumOrderByCreatedAtDesc(Long boardNum);

    List<Reply> findAllByBoardNum(Long boardNum);

    Reply findByBoardNumAndReplyNum(Long boardNum, Long ReplyNum);

    @Transactional
    Long deleteByBoardNumAndReplyNum(Long boardNum, Long ReplyNum);
}
