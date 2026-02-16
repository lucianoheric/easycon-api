package com.tribosoftec.easycon_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.dtos.requests.PersonRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonResponseDto;
import com.tribosoftec.easycon_api.services.PersonService;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RequestMapping("/api/person")
@RestController
public class PersonController {

    private final PersonService personService;  

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping   
    public PersonResponseDto createPerson(@RequestBody PersonRequestDto person) {                
        return personService.createPerson(person);
    }
    
    @GetMapping
    public List<PersonResponseDto> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/{id}")
    public PersonResponseDto getPersonById(@PathVariable Long id) {
        return personService.findPersonById(id);    
    }

    @GetMapping("/email/{email}")
    public PersonResponseDto getPersonByEmail(@PathVariable String email) {
        return personService.findPersonByEmail(email);    
    }

}
