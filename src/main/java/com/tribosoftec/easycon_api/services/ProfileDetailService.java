package com.tribosoftec.easycon_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.ProfileDetail;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileDetailResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidentResponseDto;
import com.tribosoftec.easycon_api.repositories.ProfileDetailRepository;

@Service
public class ProfileDetailService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileDetailRepository profileDetailRepository;

    public ProfileDetailResponseDto getProfileDetail(ProfileDetail profileDetail) {
        try{
            ProfileResponseDto  profileDto = profileService.getProfile(profileDetail.getProfile());
            ResidentResponseDto residentDto = profileDetail.getResident() != null ? profileDetail.getResident().toResponseDto() : null;

            ProfileDetailResponseDto responseDto = new ProfileDetailResponseDto();
            responseDto.setId(profileDetail.getId());
            responseDto.setProfile(profileService.getProfile(profileDetail.getProfile()));
            responseDto.setResident(profileDetail.getResident() != null ? profileDetail.getResident().toResponseDto() : null);
            responseDto.setStarted_at(profileDetail.getStartedAt());
            responseDto.setEnded_at(profileDetail.getEndedAt());
            responseDto.setActive(profileDetail.getActive());
            responseDto.setCreated_at(profileDetail.getCreatedAt());
            responseDto.setUpdated_at(profileDetail.getUpdatedAt());
            return responseDto;            

        } catch (Exception e) {
            throw new RuntimeException("Error fetching profile detail", e);

        }
    }



}
