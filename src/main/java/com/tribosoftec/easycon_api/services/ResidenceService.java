package com.tribosoftec.easycon_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.Residence;
import com.tribosoftec.easycon_api.domain.dtos.requests.ResidenceRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidenceResponseDto;
import com.tribosoftec.easycon_api.repositories.ResidenceRepository;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private ResidenceGroupService residenceGroupService;


    public ResidenceResponseDto getResidence(Residence residence) {
        try {
            ResidenceResponseDto responseDto = new ResidenceResponseDto();
            responseDto.setId(residence.getId());
            responseDto.setNumber(residence.getNumber());
            responseDto.setDescription(residence.getDescription());
            responseDto.setActive(residence.getActive());
            responseDto.setResidenceGroup(residenceGroupService.getResidenceGroup(residence.getResidenceGroup()));
            responseDto.setCreated_at(residence.getCreatedAt());
            responseDto.setUpdated_at(residence.getUpdatedAt());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error converting residence: " + e.getMessage());
        }
    }

    public Residence findById(Long id) {
        try {
            return residenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Residence not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence: " + e.getMessage());
        }
    }

    public ResidenceResponseDto findResidenceById(Long id) {
        try {
            var residence = residenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Residence not found"));
            return getResidence(residence);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence: " + e.getMessage());
        }
    }

    public ResidenceResponseDto createResidence(ResidenceRequestDto requestDto) {
        try {
            var residenceGroup = residenceGroupService.findById(requestDto.getResidenceGroupId());
            Residence residence = Residence.builder()
                    .number(requestDto.getNumber())
                    .description(requestDto.getDescription())
                    .active(requestDto.getActive())
                    .residenceGroup(residenceGroup)
                    .build();
            residenceRepository.save(residence);
            return getResidence(residence);
        } catch (Exception e) {
            throw new RuntimeException("Error creating residence: " + e.getMessage());
        }
    }   

    public ResidenceResponseDto updateResidence(ResidenceRequestDto requestDto) {
        try {
            var residence = residenceRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Residence not found"));
            var residenceGroup = residenceGroupService.findById(requestDto.getResidenceGroupId());
            residence.setNumber(requestDto.getNumber());
            residence.setDescription(requestDto.getDescription());
            residence.setActive(requestDto.getActive());
            residence.setResidenceGroup(residenceGroup);
            residenceRepository.save(residence);
            return getResidence(residence);
        } catch (Exception e) {
            throw new RuntimeException("Error updating residence: " + e.getMessage());
        }
    }

    public ResidenceResponseDto setActiveResidence(ResidenceRequestDto requestDto) {
        try {
            var residence = residenceRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Residence not found"));
            residence.setActive(requestDto.getActive());
            residenceRepository.save(residence);
            return getResidence(residence);
        } catch (Exception e) {
            throw new RuntimeException("Error updating residence active status: " + e.getMessage());
        }
    }

    public List<ResidenceResponseDto> findResidenceByResidenceGroupId(Long residenceGroupId) {
        try {
            var residences = residenceRepository.findByResidenceGroupId(residenceGroupId);
            return residences;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence by residence group ID: " + e.getMessage());
        }
    }

}
