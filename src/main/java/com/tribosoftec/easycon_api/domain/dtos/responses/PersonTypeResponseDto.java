package com.tribosoftec.easycon_api.domain.dtos.responses;

public class PersonTypeResponseDto {

    private Long id;
    private String name;
    private String description;

    public PersonTypeResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PersonTypeResponseDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }  

}
