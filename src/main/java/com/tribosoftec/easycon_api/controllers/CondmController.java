package com.tribosoftec.easycon_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tribosoftec.easycon_api.domain.dtos.requests.CondmRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.CondmResponseDto;
import com.tribosoftec.easycon_api.services.CondmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/condm")
@RestController
public class CondmController {

    @Autowired
    private CondmService condmService;

    @GetMapping("/{id}")
    public CondmResponseDto getCondm(@PathVariable Long id) {
        return condmService.getCondm(condmService.findById(id));
    }

    @PostMapping
    public CondmResponseDto createCondm(@RequestBody CondmRequestDto requestDto) {
        return condmService.createCondm(requestDto);
    }
    
    @PutMapping
    public CondmResponseDto putMethodName(@RequestBody CondmRequestDto requestDto) {
        return condmService.updateCondm(requestDto);
    }

    @PostMapping("/active")
    public CondmResponseDto updateActiveStatus(@RequestBody CondmRequestDto requestDto) {
        /* {"id": 1, "active": true} */
        return condmService.setActiveCondm(requestDto);
    }

}
