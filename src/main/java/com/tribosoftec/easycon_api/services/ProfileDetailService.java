package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.Profile;
import com.tribosoftec.easycon_api.domain.ProfileDetail;
import com.tribosoftec.easycon_api.domain.Resident;
import com.tribosoftec.easycon_api.domain.dtos.requests.ProfileDetailRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileDetailResponseDto;
import com.tribosoftec.easycon_api.repositories.ProfileDetailRepository;

@Service
public class ProfileDetailService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private ProfileDetailRepository profileDetailRepository;

    public ProfileDetailResponseDto getProfileDetail(ProfileDetail profileDetail) {
        try{
            ProfileDetailResponseDto responseDto = new ProfileDetailResponseDto();
            responseDto.setId(profileDetail.getId());
            responseDto.setProfile(profileService.getProfile(profileService.findById(profileDetail.getProfile().getId())));
            responseDto.setResident(residentService.getResident(residentService.findById(profileDetail.getResident().getId())));
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


    public List<ProfileDetailResponseDto> getProfileDetailList(List<ProfileDetail> profileDetail) {
        try {
            List<ProfileDetailResponseDto> responseDto =
                profileDetail.stream()
                        .map(this::getProfileDetail)
                        .collect(Collectors.toList());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error converting profile detail: " + e.getMessage());
        }
    }


    public ProfileDetail findById(Long id) {
        try {
            return profileDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile detail not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching profile detail: " + e.getMessage());
        }
    }


    public ProfileDetailResponseDto findProfileDetailById(Long id) {
        try {
            var profileDetail = profileDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile detail not found"));
            return getProfileDetail(profileDetail);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching profile detail: " + e.getMessage());
        }
    }


    public ProfileDetailResponseDto createProfileDetail(ProfileDetailRequestDto requestDto) {
        try {
            Profile profile = profileService.findById(requestDto.getProfileId());
            Resident resident = residentService.findById(requestDto.getResidentId());
            ProfileDetail profileDetail = ProfileDetail.builder()
                    .profile(Profile.builder().id(profile.getId()).build())
                    .resident(Resident.builder().id(resident.getId()).build())
                    .startedAt(requestDto.getStartedAt())
                    .endedAt(requestDto.getEndedAt())
                    .active(true)
                    .build();
            profileDetailRepository.save(profileDetail);
            return getProfileDetail(profileDetail);
        } catch (Exception e) {
            throw new RuntimeException("Error creating profile detail: " + e.getMessage());
        }
    }


    public ProfileDetailResponseDto updateProfileDetail(ProfileDetailRequestDto requestDto) {
        try {
            ProfileDetail profileDetail = profileDetailRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Profile detail not found"));
            Profile profile = profileService.findById(requestDto.getProfileId());
            Resident resident = residentService.findById(requestDto.getResidentId());
            profileDetail.setProfile(Profile.builder().id(profile.getId()).build());
            profileDetail.setResident(Resident.builder().id(resident.getId()).build());
            profileDetail.setStartedAt(requestDto.getStartedAt());
            profileDetail.setEndedAt(requestDto.getEndedAt());
            profileDetail.setActive(requestDto.getActive());
            profileDetailRepository.save(profileDetail);
            return getProfileDetail(profileDetail);
        } catch (Exception e) {
            throw new RuntimeException("Error updating profile detail: " + e.getMessage());
        }
    }


    public ProfileDetailResponseDto setActiveProfileDetail(ProfileDetailRequestDto requestDto) {
        try {
            if (!profileDetailRepository.existsById(requestDto.getId())) {
                throw new RuntimeException("Profile detail not found with ID: " + requestDto.getId());
            }
            ProfileDetail profileDetail = profileDetailRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Profile detail not found"));
            ProfileDetailResponseDto responseDto = getProfileDetail(profileDetail);
            profileDetail.setActive(requestDto.getActive());
            profileDetailRepository.save(profileDetail);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error setting active status of profile detail: " + e.getMessage());
        }        
    }


    public List<ProfileDetailResponseDto> findByProfileId(Long profileId) {
        try {
            List<ProfileDetail> profileDetails = profileDetailRepository.findByProfileId(profileId);
            return getProfileDetailList(profileDetails);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching profile details by profile ID: " + e.getMessage());
        }
    }

}
