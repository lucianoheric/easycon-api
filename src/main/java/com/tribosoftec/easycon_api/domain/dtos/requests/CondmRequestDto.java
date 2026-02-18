package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondmRequestDto {


    private Long id;
    private Long person_id;
    private String name;       
    private String description;
    private String cep;
    private String street;
    private String number;
    private String complement;
    private Boolean active;

}
