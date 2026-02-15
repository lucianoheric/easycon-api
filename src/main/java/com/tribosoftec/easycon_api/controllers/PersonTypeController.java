package com.tribosoftec.easycon_api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.PersonType;
import com.tribosoftec.easycon_api.services.PersonTypeService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/api/person-types")
@RestController
public class PersonTypeController {

    private final PersonTypeService personTypeService;

    public PersonTypeController(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    @PostMapping
    public PersonType create(@RequestBody PersonType personType) {
        try {
            return personTypeService.create(personType);
        } catch (Exception e) {
            throw new RuntimeException("Error creating person type", e);
        }
    }

  
    @GetMapping("/{id}")
    public PersonType getPersonTypeById(@PathVariable Long id) {
        try {
            return personTypeService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving person type by ID", e);
        }
    }


    @GetMapping    
    public List<PersonType> findAllPersonTypes() {
        try {
            return personTypeService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all person types", e);
        }
    }

    
    @DeleteMapping("/{id}")
    public PersonType deletePersonType(@PathVariable Long id) {
        try {
            return personTypeService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person type", e);        
        }
    }

}
