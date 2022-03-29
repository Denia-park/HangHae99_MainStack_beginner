package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.dto.FreeTableRequestDto;
import com.sparta.springbeginnerhomework.dto.ReplyRequestDto;
import com.sparta.springbeginnerhomework.model.FreeTable;
import com.sparta.springbeginnerhomework.model.Reply;
import com.sparta.springbeginnerhomework.repository.FreeTableRepository;
import com.sparta.springbeginnerhomework.repository.ReplyRepository;
import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyRepository replyRepository;
    
    @PostMapping("/api/replies/{boardNum}")
    public Reply postReply(@PathVariable Long boardNum, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails != null){
            Long replyNum = (long)(replyRepository.findAllByBoardNum(boardNum).size() + 1);
            Reply reply = new Reply(boardNum,replyNum ,requestDto, userDetails);
            replyRepository.save(reply);
            return reply;
        }
        return null;
    }
}
