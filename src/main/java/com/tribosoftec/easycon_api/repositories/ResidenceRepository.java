package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.Residence;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidenceResponseDto;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    
    List<ResidenceResponseDto> findByResidenceGroupId(Long residenceGroupId);
}
