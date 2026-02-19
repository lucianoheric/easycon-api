package com.tribosoftec.easycon_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.dtos.requests.ResidenceRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidenceResponseDto;
import com.tribosoftec.easycon_api.services.ResidenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/residence")
@RestController
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;


    @GetMapping("/{id}")
    public ResidenceResponseDto getResidenceById(@PathVariable Long id) {
        return this.findById(id);
    }
    
    public ResidenceResponseDto findById(Long id) {
        try {
            return residenceService.findResidenceById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence: " + e.getMessage());
        }
    }

    @PostMapping
    public ResidenceResponseDto createResidence(ResidenceRequestDto requestDto) {
        try {
            return residenceService.createResidence(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("Error creating residence: " + e.getMessage());
        }
    }

    @PutMapping
    public ResidenceResponseDto updateResidence(ResidenceRequestDto requestDto) {
        try {
            return residenceService.updateResidence(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("Error updating residence: " + e.getMessage());
        }
    }
    
    @GetMapping("/by-group/{residenceGroupId}")
    public List<ResidenceResponseDto> findResidenceByResidenceGroupId(@PathVariable Long residenceGroupId) {
        try {
            return residenceService.findResidenceByResidenceGroupId(residenceGroupId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence by residence group ID: " + e.getMessage());
        }
    }

    @PostMapping("/active")
    public ResidenceResponseDto setActiveResidence(ResidenceRequestDto requestDto) {
        try {
            return residenceService.setActiveResidence(requestDto);
        } catch (Exception e) {
            throw new RuntimeException("Error setting residence active status: " + e.getMessage());
        }
    }

}
