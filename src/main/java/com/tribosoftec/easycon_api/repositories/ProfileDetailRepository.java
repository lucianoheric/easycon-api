package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.ProfileDetail;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileDetailResponseDto;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Long> {

    List<ProfileDetailResponseDto> findByProfileId(Long profileId);
}
