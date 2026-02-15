package com.tribosoftec.easycon_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tribosoftec.easycon_api.domain.PersonType;

public interface PersonTypeRepository extends JpaRepository<PersonType, Long> {

}
