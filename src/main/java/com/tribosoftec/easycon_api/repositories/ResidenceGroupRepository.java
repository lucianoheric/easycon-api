package com.tribosoftec.easycon_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.ResidenceGroup;

public interface ResidenceGroupRepository extends JpaRepository<ResidenceGroup, Long> {

    List<ResidenceGroup> findByCondmId(Long condmId);

}
