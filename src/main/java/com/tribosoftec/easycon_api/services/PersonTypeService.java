package com.tribosoftec.easycon_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.PersonType;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonTypeResponseDto;
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

    public PersonTypeResponseDto delete(Long id) {
        try {
            if (!personTypeRepository.existsById(id)) {
                throw new RuntimeException("Person type not found with ID: " + id);
            }
            PersonType personType = this.findById(id);
            PersonTypeResponseDto responseDto = new PersonTypeResponseDto(id, null, null);
            responseDto.setId(personType.getId().longValue());
            responseDto.setName(personType.getName());
            responseDto.setDescription(personType.getDescription());
            personTypeRepository.deleteById(id);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person type", e);
        }
    }
    
    public PersonTypeResponseDto update(PersonTypeResponseDto personType) {
        try {
            PersonType existingPersonType = this.findById(personType.getId());
            existingPersonType.setName(personType.getName());
            existingPersonType.setDescription(personType.getDescription());
            PersonType updatedPersonType = personTypeRepository.save(existingPersonType);
            return new PersonTypeResponseDto(updatedPersonType.getId().longValue(), updatedPersonType.getName(), updatedPersonType.getDescription());
        } catch (Exception e) {
            throw new RuntimeException("Error updating person type", e);
        }
    }

}
