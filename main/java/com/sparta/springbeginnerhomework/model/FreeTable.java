package com.sparta.springbeginnerhomework.model;

import com.sparta.springbeginnerhomework.dto.FreeTableRequestDto;
import com.sparta.springbeginnerhomework.modelInterface.Timestamped;
import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class FreeTable extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    public FreeTable(FreeTableRequestDto requestDto, UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.username = userDetails.getUsername();
        this.content = requestDto.getContent();
    }
}
