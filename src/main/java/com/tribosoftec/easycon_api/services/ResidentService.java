package com.tribosoftec.easycon_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.Resident;
import com.tribosoftec.easycon_api.domain.dtos.requests.ResidentRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidentResponseDto;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;
    
    @Autowired
    private ResidenceService residenceService;


    public Resident findById(Long id) {
        try {
            return residentRepository.findById(id).orElseThrow(() -> new RuntimeException("Resident not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching resident: " + e.getMessage());
        }
    }
    

    public ResidentResponseDto getResidentById(Long id) {
        Resident resident = findById(id);
        return getResident(resident);
    }


    public ResidentResponseDto getResident(Resident resident) {
        if (resident == null) {
            return null;
        }
        ResidentResponseDto responseDto = new ResidentResponseDto();
        responseDto.setId(resident.getId());
        return responseDto;
    }


    public ResidentResponseDto createResident(ResidentRequestDto requestDto) {
        try {
            Resident resident = new Resident(); 
            resident.setName(requestDto.getName());
            resident.setObservation(requestDto.getObservation());
            resident.setResidence(residenceService.findById(requestDto.getResidence_id()));
            resident.setIsDefault(requestDto.getIs_default());
            Resident savedResident = residentRepository.save(resident);
            return getResident(savedResident);
        } catch (Exception e) {
            throw new RuntimeException("Error creating resident: " + e.getMessage());
        }
    }


    public ResidentResponseDto updateResident(ResidentRequestDto requestDto) {
        try {
            Resident resident = findById(requestDto.getId());
            resident.setName(requestDto.getName());
            resident.setObservation(requestDto.getObservation());
            resident.setResidence(residenceService.findById(requestDto.getResidence_id()));
            resident.setIsDefault(requestDto.getIs_default());
            Resident updatedResident = residentRepository.save(resident);
            return getResident(updatedResident);
        } catch (Exception e) {
            throw new RuntimeException("Error updating resident: " + e.getMessage());
        }
    }


    public ResidentResponseDto setActiveResident(ResidentRequestDto requestDto) {
        try {
            Resident resident = findById(requestDto.getId());
            resident.setActive(requestDto.getActive());
            Resident updatedResident = residentRepository.save(resident);
            return getResident(updatedResident);
        } catch (Exception e) {
            throw new RuntimeException("Error setting active resident: " + e.getMessage());
        }
    }


    public List<ResidentResponseDto> findByResidenceId(Long residenceId) {
        try {
            return residentRepository.findByResidenceId(residenceId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching residents by residence ID: " + e.getMessage());
        }
    }

}
