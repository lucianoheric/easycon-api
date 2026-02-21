package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.Residence;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    
    List<Residence> findByResidenceGroupId(Long residenceGroupId);
}
