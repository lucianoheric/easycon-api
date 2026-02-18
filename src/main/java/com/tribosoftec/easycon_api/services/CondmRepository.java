package com.tribosoftec.easycon_api.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.Condm;

public interface CondmRepository extends JpaRepository<Condm, Long>{
    
}
