package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.Person;
import com.tribosoftec.easycon_api.domain.PersonType;
import com.tribosoftec.easycon_api.domain.dtos.requests.PersonRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.requests.PersonRequestUpdateDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonTypeResponseDto;
import com.tribosoftec.easycon_api.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    private PersonTypeService personTypeService;

    public PersonResponseDto getPerson(Person person){
        PersonType personType = person.getPersonType();
        PersonTypeResponseDto personTypeResponseDto = new PersonTypeResponseDto(
                personType.getId().longValue(),
                personType.getName(),
                personType.getDescription()
        );
        return new PersonResponseDto(
                person.getId(),
                person.getName(),
                person.getShortName(),
                personTypeResponseDto,
                person.getDocument(),
                person.getActive(),
                person.getEmail(),
                person.getActivatedAt(),
                person.getCreatedAt(),
                person.getUpdatedAt()
        );
    }
           
    public PersonResponseDto createPerson(PersonRequestDto person) {
        try {   
            Person newPerson = new Person();
            newPerson.setName(person.getName());
            newPerson.setEmail(person.getEmail());
            newPerson.setShortName(person.getShortName());
            newPerson.setDocument(person.getDocument());
            newPerson.setActive(person.isActive()); 
            PersonType personType = personTypeService.findById(person.getPersonTypeId());
            newPerson.setPersonType(personType);
            Person person2 = personRepository.save(newPerson);

            PersonResponseDto responseDto = new PersonResponseDto();
            responseDto.setId(person2.getId());
            responseDto.setName(person2.getName());
            responseDto.setShortName(person2.getShortName());
            responseDto.setEmail(person2.getEmail());
            responseDto.setDocument(person2.getDocument());
            responseDto.setActive(person2.getActive());
            responseDto.setCreated_at(person2.getCreatedAt());
            responseDto.setActivated_at(person2.getActivatedAt());
            responseDto.setPersonType(new PersonTypeResponseDto(personType.getId().longValue(), 
                                                                personType.getName(), 
                                                                personType.getDescription()));
            responseDto.setUpdated_at(person2.getUpdatedAt());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error creating person", e);
        }
    }  
    
    
    public List<PersonResponseDto> findAllPersons() {
        try {
            List<Person> persons = personRepository.findAll();
            return persons.stream().map(person -> {
                PersonType personType = person.getPersonType();
                PersonTypeResponseDto personTypeResponseDto = new PersonTypeResponseDto(
                        personType.getId().longValue(),
                        personType.getName(),
                        personType.getDescription()
                );
                return new PersonResponseDto(
                        person.getId(),
                        person.getName(),
                        person.getShortName(),
                        personTypeResponseDto,
                        person.getDocument(),
                        person.getActive(),
                        person.getEmail(),
                        person.getActivatedAt(),
                        person.getCreatedAt(),
                        person.getUpdatedAt()
                );
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all persons", e);
        }
    }


    public PersonResponseDto findPersonById(Long id) {
        try {
            Person person = personRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
            return this.getPerson(person);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving person by ID", e);
        }
    }


    public PersonResponseDto findPersonByEmail(String email) {
        try {
            Person person = personRepository.findByEmail(email);
            if (person == null) {
                throw new RuntimeException("Person not found with email: " + email);
            }            
            return this.getPerson(person);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving person by email", e);
        }
    }


    public PersonResponseDto deletePerson(Long id) {
        try {
            if (!personRepository.existsById(id)) {
                throw new RuntimeException("Person not found with ID: " + id);
            }
            Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
            PersonResponseDto responseDto = this.getPerson(person);
            personRepository.deleteById(id);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person", e);
        }
    }


    public PersonResponseDto setPersonActivatedAt(Long id) {
        try {
            Person person = personRepository.setActivatedAtPerson(id);
            return this.getPerson(person);
        } catch (Exception e) {
            throw new RuntimeException("Error setting person's activated_at", e);
        }
    }


    public PersonResponseDto updatePerson(PersonRequestUpdateDto person) {
        try {
            Person existingPerson = personRepository.findById(person.getId())
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + person.getId()));
            existingPerson.setName(person.getName());
            existingPerson.setShortName(person.getShortName());
            existingPerson.setEmail(person.getEmail());
            existingPerson.setDocument(person.getDocument());
            existingPerson.setActive(person.isActive());
            PersonType personType = personTypeService.findById(person.getPersonTypeId());
            existingPerson.setPersonType(personType);
            Person updatedPerson = personRepository.save(existingPerson);
            return this.getPerson(updatedPerson);
        } catch (Exception e) {
            throw new RuntimeException("Error updating person", e);
        }
    }

}
