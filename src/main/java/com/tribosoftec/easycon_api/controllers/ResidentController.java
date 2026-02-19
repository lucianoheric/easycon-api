package com.tribosoftec.easycon_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.dtos.requests.ResidentRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidentResponseDto;
import com.tribosoftec.easycon_api.services.ResidentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;





@RequestMapping("/api/resident")
@RestController
public class ResidentController {

    @Autowired
    private ResidentService residentService;


    @GetMapping("/{id}")
    public ResidentResponseDto getResidentById(@PathVariable Long id) {
        return residentService.getResidentById(id);
    }


    @GetMapping("/by-residence/{residenceId}")
    public List<ResidentResponseDto> getResidentsByResidenceId(@PathVariable Long residenceId) {
        return residentService.findByResidenceId(residenceId);
    }
    
    
    @PostMapping
    public ResidentResponseDto createResident(@RequestBody ResidentRequestDto requestDto) {
        return residentService.createResident(requestDto);
    }


    @PutMapping
    public ResidentResponseDto updateResident(@RequestBody ResidentRequestDto requestDto) {
        return residentService.updateResident(requestDto);
    }

    @PostMapping("/active")
    public ResidentResponseDto setActiveResident(@RequestBody ResidentRequestDto requestDto) {
        return residentService.setActiveResident(requestDto);
    }
    

}
