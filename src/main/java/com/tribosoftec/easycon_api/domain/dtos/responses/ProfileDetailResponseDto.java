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
public class ProfileDetailResponseDto {

    private Integer id;
    private ProfileResponseDto profile;
    private ResidentResponseDto resident;
    private Timestamp started_at;
    private Timestamp ended_at;
    private Boolean active;
    private Timestamp created_at;
    private Timestamp updated_at;
    

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

    public String getStarted_at_at() {
        return started_at != null
                ? BR_FORMAT.format(started_at.toInstant())
                : null;
    }

    public void setStarted_at(Timestamp started_at) {
        this.started_at = started_at;
    }

    public String getEnded_at() {
        return ended_at != null
                ? BR_FORMAT.format(ended_at.toInstant())
                : null;
    }

    public void setEnded_at(Timestamp ended_at) {
        this.ended_at = ended_at;
    } 

}
