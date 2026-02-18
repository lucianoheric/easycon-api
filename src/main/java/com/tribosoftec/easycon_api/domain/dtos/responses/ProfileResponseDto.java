package com.tribosoftec.easycon_api.domain.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

    private Long id;
    private String name;    
    private String description;
    private boolean active;
    private Timestamp created_at;
    private Timestamp updated_at;  
    private PersonResponseDto person;
    
    private static final DateTimeFormatter BR_FORMAT =
    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            .withZone(ZoneId.of("America/Sao_Paulo"));
            
    
    public String getCreated_at() {
        return created_at != null
                ? BR_FORMAT.format(created_at.toInstant())
                : null;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at != null
                ? BR_FORMAT.format(updated_at.toInstant())
                : null;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }            
    
}
