package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDto {

    private Long id;
    private String name;    
    private String description;
    private boolean active;
    private Long personId;

}
