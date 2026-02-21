package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.ProfileDetail;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Long> {

    List<ProfileDetail> findByProfileId(Long profileId);
}
