package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmLoginRequestDto {

    private Long id;
    private Long person_id;
    private String name;       
    private String email;
    private String passwd;
    private Boolean alter_passwd;
    private Boolean active;

}