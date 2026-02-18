package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceGroupRequestDto {

    private Long id;
    private Long condm_id;
    private String name;
    private String description;
    private String observation;
    private Boolean active;

}
