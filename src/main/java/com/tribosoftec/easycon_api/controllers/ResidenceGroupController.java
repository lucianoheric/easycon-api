package com.tribosoftec.easycon_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tribosoftec.easycon_api.domain.Residence;
import com.tribosoftec.easycon_api.domain.dtos.requests.ResidenceGroupRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ResidenceGroupResponseDto;
import com.tribosoftec.easycon_api.services.ResidenceGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RequestMapping("/api/residence-groups")
@RestController
public class ResidenceGroupController {

    @Autowired
    private ResidenceGroupService residenceGroupService;

    @GetMapping("/{id}")
    public ResidenceGroupResponseDto findResidenceGroupById(@PathVariable Long id) {
        return residenceGroupService.getResidenceGroup(residenceGroupService.findById(id));
    }

    @GetMapping("/by-condm/{condmId}")
    public List<ResidenceGroupResponseDto> findResidenceGroupByCondmId(@PathVariable Long condmId) {
        return residenceGroupService.findAllResidenceGroupsByCondmId(condmId);
    }
    
    @PostMapping
    public ResidenceGroupResponseDto createResidenceGroup(@RequestBody ResidenceGroupRequestDto residenceGroup) {
        return residenceGroupService.createResidenceGroup(residenceGroup);
    }

    @PutMapping
    public ResidenceGroupResponseDto updateResidenceGroup(@RequestBody ResidenceGroupRequestDto residenceGroup) {
        return residenceGroupService.updateResidenceGroup(residenceGroup);
    }

    @PostMapping("/active")
    public ResidenceGroupResponseDto updateActiveStatus(@RequestBody ResidenceGroupRequestDto residenceGroup) {
        /* {"id": 1, "active": true} */
        return residenceGroupService.setActiveResidenceGroup(residenceGroup);
    }
    
    
}
