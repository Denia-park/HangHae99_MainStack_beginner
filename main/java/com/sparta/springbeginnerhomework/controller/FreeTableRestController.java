package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.models.FreeTable;
import com.sparta.springbeginnerhomework.models.FreeTableRepository;
import com.sparta.springbeginnerhomework.models.FreeTableRequestDto;
import lombok.RequiredArgsConstructor;
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
    public FreeTable postTable(@RequestBody FreeTableRequestDto freeTableRequestDto){
        FreeTable freeTable = new FreeTable(freeTableRequestDto);
        return freeTableRepository.save(freeTable);
    }


}
