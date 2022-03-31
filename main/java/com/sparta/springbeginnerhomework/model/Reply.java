package com.sparta.springbeginnerhomework.model;

import com.sparta.springbeginnerhomework.dto.ReplyRequestDto;
import com.sparta.springbeginnerhomework.dto.ReplyUpdateDto;
import com.sparta.springbeginnerhomework.modelInterface.Timestamped;
import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long boardNum;

    @Column(nullable = false)
    private Long replyNum;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String replyContent;

    public Reply(Long boardNum, Long replyNum ,ReplyRequestDto requestDto, UserDetailsImpl userDetails) {
        this.boardNum = boardNum;
        this.replyNum = replyNum;
        this.username = userDetails.getUsername();
        this.replyContent = requestDto.getReplyContent();
    }

    public Reply(ReplyUpdateDto requestDto, UserDetailsImpl userDetails) {
        this.boardNum = requestDto.getBoardNum();
        this.replyNum = requestDto.getReplyNum();
        this.username = userDetails.getUsername();
        this.replyContent = requestDto.getReplyContent();
    }

    public void setReplyContent(String updateContent) {
        this.replyContent = updateContent;
    }
}
