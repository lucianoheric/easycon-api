package com.tribosoftec.easycon_api.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceRequestDto {

    private Long id;
    private Long residenceGroupId;
    private String number;
    private String description;

}
