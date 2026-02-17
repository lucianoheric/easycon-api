package com.tribosoftec.easycon_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tribosoftec.easycon_api.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM person p WHERE p.email = :email", nativeQuery = true)
    Person findByEmail(String email);

    @Query(value = "UPDATE person SET activated_at = NOW() WHERE id = :id RETURNING *", nativeQuery = true)
    Person setActivatedAtPerson(Long id);    
}
