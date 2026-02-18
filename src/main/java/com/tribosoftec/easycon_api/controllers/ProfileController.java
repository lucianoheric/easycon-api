package com.tribosoftec.easycon_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.dtos.requests.ProfileRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileResponseDto;
import com.tribosoftec.easycon_api.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/profile")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // Create a new profile
    @PostMapping
    public ProfileResponseDto createProfile(@RequestBody ProfileRequestDto entity) {        
        return profileService.createProfile(entity);
    }

    // Get a profile by ID
    @GetMapping("/{id}")
    public ProfileResponseDto getProfileById(@PathVariable Long id) {
        return profileService.findById(id);
    }    

    // Update an existing profile
    @PutMapping
    public ProfileResponseDto updateProfile(@RequestBody ProfileRequestDto entity) {        
        return profileService.update(entity);
    }

    // Delete a profile by ID
    @DeleteMapping("/{id}")
    public ProfileResponseDto deleteProfile(@PathVariable Long id) {
        return profileService.delete(id);   
    }

    // Get all profiles    
    @GetMapping("/person/{personId}")
    public java.util.List<ProfileResponseDto> getAllProfiles(@PathVariable Long personId) {
        return profileService.findProfileByPersonId(personId);
    }
    
}