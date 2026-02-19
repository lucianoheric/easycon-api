package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRequestDto {

    private Long id;
    private Long residence_id;
    private String name;
    private String observation;
    private Boolean is_default;
    private Boolean active;

}
