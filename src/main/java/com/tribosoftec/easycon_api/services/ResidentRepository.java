package com.tribosoftec.easycon_api.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.Resident;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidentResponseDto;

public interface ResidentRepository extends JpaRepository<Resident, Long>{

    List<ResidentResponseDto> findByResidenceId(Long residenceId);

}
