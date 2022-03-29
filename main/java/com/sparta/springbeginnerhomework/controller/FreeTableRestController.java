package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.model.FreeTable;
import com.sparta.springbeginnerhomework.repository.FreeTableRepository;
import com.sparta.springbeginnerhomework.dto.FreeTableRequestDto;
import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FreeTableRestController {

    private final FreeTableRepository freeTableRepository;

    @GetMapping("/api/freetables")
    public List<FreeTable> getTable(){
        return freeTableRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/freetables/{id}")
    public FreeTable getTable(@PathVariable Long id){
        FreeTable freeTable = freeTableRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        return freeTable;
    }

    @PostMapping("/api/freetables")
    public FreeTable postTable(@RequestBody FreeTableRequestDto freeTableRequestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails != null){
            FreeTable freeTable = new FreeTable(freeTableRequestDto,userDetails);
            return freeTableRepository.save(freeTable);
        }
        return null;
    }


}
