package com.tribosoftec.easycon_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.dtos.requests.ProfileDetailRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileDetailResponseDto;
import com.tribosoftec.easycon_api.services.ProfileDetailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RequestMapping("/api/profile-detail")
@RestController
public class ProfileDetailController {

    @Autowired
    private ProfileDetailService profileDetailService;


    @GetMapping("/{id}")
    public ProfileDetailResponseDto findProfileDetailById(@PathVariable Long id) {
        return profileDetailService.findProfileDetailById(id);
    }
    

    @PostMapping
    public ProfileDetailResponseDto createProfileDetail(@RequestBody ProfileDetailRequestDto requestDto) {        
        return profileDetailService.createProfileDetail(requestDto);
    }
    

    @PutMapping
    public ProfileDetailResponseDto updateProfileDetail(@RequestBody ProfileDetailRequestDto requestDto) {
        return profileDetailService.updateProfileDetail(requestDto);
    }

    @GetMapping("/by-profile/{profileId}")
    public List<ProfileDetailResponseDto> getProfileDetailsByProfileId(@PathVariable Long profileId) {
        return profileDetailService.findByProfileId(profileId);
    }
    

    @PostMapping("/active")
    public ProfileDetailResponseDto setActiveProfileDetail(@RequestBody ProfileDetailRequestDto requestDto) {
         return profileDetailService.setActiveProfileDetail(requestDto);
    }
    
}
