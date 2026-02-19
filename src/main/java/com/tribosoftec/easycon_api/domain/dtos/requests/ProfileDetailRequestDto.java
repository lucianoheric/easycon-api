package com.tribosoftec.easycon_api.domain.dtos.requests;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetailRequestDto {

    private Long id;
    private Long profileId;
    private Long residentId;
    private Date startedAt; 
    private Date endedAt;  
    private Boolean active;

}
