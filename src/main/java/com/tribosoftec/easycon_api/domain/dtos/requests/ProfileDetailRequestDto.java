package com.tribosoftec.easycon_api.domain.dtos.requests;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetailRequestDto {

    private Integer profileId;
    private Integer residentId;
    private Timestamp startedAt; 
    private Timestamp endedAt;   

}
