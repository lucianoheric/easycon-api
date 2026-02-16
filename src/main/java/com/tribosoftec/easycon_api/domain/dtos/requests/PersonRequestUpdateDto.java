package com.tribosoftec.easycon_api.domain.dtos.requests;


public class PersonRequestUpdateDto {

    private Long id;
    private String name;
    private String shortName;
    private String email;
    private Long personTypeId;
    private String document;
    private boolean active;
    private java.sql.Timestamp activated_at;

    public PersonRequestUpdateDto(Long id, String name, String shortName, String email, Long personTypeId, String document, boolean active, java.sql.Timestamp activated_at) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.email = email;
        this.personTypeId = personTypeId;
        this.document = document;
        this.active = active;
        this.activated_at = activated_at;
    }

    public PersonRequestUpdateDto() {
        //TODO Auto-generated constructor stub
    }

    // Getters and setters
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

    public String getEmail() {
        return email;
    }           

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Long personTypeId) {
        this.personTypeId = personTypeId;
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

    public java.sql.Timestamp getActivated_at() {
        return activated_at;
    }

    public void setActivated_at(java.sql.Timestamp activated_at) {
        this.activated_at = activated_at;
    }

}
