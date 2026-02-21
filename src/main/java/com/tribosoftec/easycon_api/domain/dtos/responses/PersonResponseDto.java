package com.tribosoftec.easycon_api.domain.dtos.responses;

import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.time.ZoneId;

public class PersonResponseDto {

    private Long id;
    private String name;
    private String shortName;  
    private PersonTypeResponseDto personType;
    private String document;
    private boolean active; 
    private java.sql.Timestamp activated_at;
    private java.sql.Timestamp created_at;
    private java.sql.Timestamp updated_at;

    private static final DateTimeFormatter BR_FORMAT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("America/Sao_Paulo"));

    public PersonResponseDto(Long id, String name, String shortName, PersonTypeResponseDto personType, String document, boolean active, Timestamp activated_at, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.personType = personType;
        this.document = document;        
        this.active = active;
        this.activated_at = activated_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
  

    public PersonResponseDto() {
        //TODO Auto-generated constructor stub
    }


    public Long getId() {
        return id;
    }                   

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public PersonTypeResponseDto getPersonType() {
        return personType;
    }

    public void setPersonType(PersonTypeResponseDto personType) {
        this.personType = personType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }       

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivated_at() {
        return activated_at != null
                ? BR_FORMAT.format(activated_at.toInstant())
                : null;
    }

    public void setActivated_at(Timestamp activated_at) {
        this.activated_at = activated_at;
    }

    public String getCreated_at() {
        return created_at != null
                ? BR_FORMAT.format(created_at.toInstant())
                : null;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at != null
                ? BR_FORMAT.format(updated_at.toInstant())
                : null;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
