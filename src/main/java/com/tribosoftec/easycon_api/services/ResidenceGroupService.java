package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.ResidenceGroup;
import com.tribosoftec.easycon_api.domain.dtos.requests.ResidenceGroupRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.CondmResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidenceGroupResponseDto;
import com.tribosoftec.easycon_api.repositories.ResidenceGroupRepository;

@Service
public class ResidenceGroupService {

    @Autowired
    private CondmService condmService;

    @Autowired
    private ResidenceGroupRepository residenceGroupRepository;


    public ResidenceGroup findById(Long id){
        return residenceGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Residence Group not found with ID: " + id));
    }

    public ResidenceGroupResponseDto getResidenceGroup(ResidenceGroup residenceGroup) {
        if (residenceGroup == null) {
            return null;
        }
        CondmResponseDto condmDto = condmService.getCondm(condmService.findById(residenceGroup.getCondm().getId()));
        ResidenceGroupResponseDto responseDto = new ResidenceGroupResponseDto();
        responseDto.setId(residenceGroup.getId());
        responseDto.setCondm(condmDto);
        responseDto.setName(residenceGroup.getName());
        responseDto.setDescription(residenceGroup.getDescription());
        responseDto.setObservation(residenceGroup.getObservation());
        responseDto.setActive(residenceGroup.getActive());
        responseDto.setCreated_at(residenceGroup.getCreatedAt());
        responseDto.setUpdated_at(residenceGroup.getUpdatedAt());
        return responseDto;
    }

    public ResidenceGroupResponseDto createResidenceGroup(ResidenceGroupRequestDto residenceGroup) {
        try {
            ResidenceGroup newResidenceGroup = new ResidenceGroup();
            newResidenceGroup.setCondm(condmService.findById(residenceGroup.getCondm_id()));
            newResidenceGroup.setName(residenceGroup.getName());
            newResidenceGroup.setDescription(residenceGroup.getDescription());
            newResidenceGroup.setObservation(residenceGroup.getObservation());
            newResidenceGroup.setActive(residenceGroup.getActive());           
            ResidenceGroup savedResidenceGroup = residenceGroupRepository.save(newResidenceGroup);
            return getResidenceGroup(savedResidenceGroup);
        } catch (Exception e) {
            throw new RuntimeException("Error creating residence group", e);
        }
    }


    public ResidenceGroupResponseDto updateResidenceGroup(ResidenceGroupRequestDto residenceGroup) {
        try {
            ResidenceGroup existingResidenceGroup = findById(residenceGroup.getId());
            existingResidenceGroup.setName(residenceGroup.getName());
            existingResidenceGroup.setDescription(residenceGroup.getDescription());
            existingResidenceGroup.setObservation(residenceGroup.getObservation());
            existingResidenceGroup.setActive(residenceGroup.getActive());
            ResidenceGroup updatedResidenceGroup = residenceGroupRepository.save(existingResidenceGroup);
            return getResidenceGroup(updatedResidenceGroup);
        } catch (Exception e) {
            throw new RuntimeException("Error updating residence group", e);
        }
    }


    public ResidenceGroupResponseDto setActiveResidenceGroup(ResidenceGroupRequestDto residenceGroup) {
        try {
            ResidenceGroup updatedResidenceGroup = this.findById(residenceGroup.getId());
            updatedResidenceGroup.setActive(residenceGroup.getActive());
            return getResidenceGroup(residenceGroupRepository.save(updatedResidenceGroup));
        } catch (Exception e) {
            throw new RuntimeException("Error updating residence group active status", e);
        }        
    }

    public List<ResidenceGroupResponseDto> findAllResidenceGroupsByCondmId(Long condmId) {
        try {
            List<ResidenceGroup> residenceGroups =
                    residenceGroupRepository.findByCondmId(condmId);
            return residenceGroups.stream()
                    .map(this::getResidenceGroup)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residence groups", e);
        }
    }


}
