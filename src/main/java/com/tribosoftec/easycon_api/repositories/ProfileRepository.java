package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tribosoftec.easycon_api.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM profile p WHERE p.person_id = :personId", nativeQuery = true)
    List<Profile> findProfileByPersonId(Long personId);    

}
