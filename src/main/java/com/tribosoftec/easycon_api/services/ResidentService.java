package com.tribosoftec.easycon_api.services;

import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.Resident;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidentResponseDto;

@Service
public class ResidentService {

    public ResidentResponseDto getResident(Resident resident) {
        if (resident == null) {
            return null;
        }
        ResidentResponseDto responseDto = new ResidentResponseDto();
        responseDto.setId(resident.getId());
        return responseDto;
    }
}
