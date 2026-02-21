package com.tribosoftec.easycon_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.AdmLogin;

import java.util.List;


public interface AdmLoginRepositrory extends JpaRepository<AdmLogin, Long>{

    List<AdmLogin> findByPersonId(Long personId);
    AdmLogin findByEmail(String email);
}
