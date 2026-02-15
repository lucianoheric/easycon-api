package com.tribosoftec.easycon_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.PersonType;
import com.tribosoftec.easycon_api.repositories.PersonTypeRepository;

@Service
public class PersonTypeService {
    
    private final PersonTypeRepository personTypeRepository;    

    public PersonTypeService(PersonTypeRepository personTypeRepository) {
        this.personTypeRepository = personTypeRepository;
    }

    public PersonType create(PersonType personType) {
        try {
            return personTypeRepository.save(personType);
        } catch (Exception e) {
            throw new RuntimeException("Error creating person type", e);
        }
    }

    public PersonType findById(Long id) {
        try {
            return personTypeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Person type not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving person type by ID", e);
        }
    }

    public List<PersonType> findAll() {
        try {
            return personTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all person types", e);
        }
    }

    public PersonType delete(Long id) {
        try {
            if (!personTypeRepository.existsById(id)) {
                throw new RuntimeException("Person type not found with ID: " + id);
            }
            PersonType personType = findById(id);
            personTypeRepository.deleteById(id);
            return personType;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person type", e);
        }
    }


}
