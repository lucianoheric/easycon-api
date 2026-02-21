package com.tribosoftec.easycon_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tribosoftec.easycon_api.domain.dtos.requests.AdmLoginRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.AdmLoginResponseDto;
import com.tribosoftec.easycon_api.services.AdmLoginService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/api/login")
@RestController
public class AdmLoginController {

    @Autowired
    private AdmLoginService admLoginService;

    @PostMapping
    public AdmLoginResponseDto createAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.createAdmLogin(requestDto);
    }

    @PutMapping
    public AdmLoginResponseDto updateAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.updateAdmLogin(requestDto);
    }

    @GetMapping("/{id}")
    public AdmLoginResponseDto findAdmLoginById(@PathVariable Long id){
        return admLoginService.findAdmLoginById(id);
    }

    @GetMapping("/by-person/{personId}")
    public List<AdmLoginResponseDto> findAdmLoginByPersonId(@PathVariable Long personId) {
        return admLoginService.findByPersonId(personId);
    }
    
    @PostMapping("/active")
    public AdmLoginResponseDto setActiveAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.setActiveAdmLogin(requestDto);
    }
    
    @PostMapping("/force-alter-passwd")
    public AdmLoginResponseDto forceAlterPasswdAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.setForceAlterPasswd(requestDto);
    }

    @PostMapping("/alter-passwd")
    public AdmLoginResponseDto alterPasswd(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.alterPasswd(requestDto);
    }

    @PostMapping("/login")
    public AdmLoginResponseDto login(@RequestBody AdmLoginRequestDto requestDto){
        return admLoginService.login(requestDto);
    }    

    @GetMapping("/teste")
    public String teste() {
        System.out.println("ENTROU NO CONTROLLER");
        return "ok";
    }

    
}
