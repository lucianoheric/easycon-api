package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long>{

    List<Resident> findByResidenceId(Long residenceId);

}
