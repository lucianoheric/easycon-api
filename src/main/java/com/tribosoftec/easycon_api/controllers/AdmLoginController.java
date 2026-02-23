package com.tribosoftec.easycon_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AdmLoginResponseDto> createAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return ResponseEntity.ok(admLoginService.createAdmLogin(requestDto));
    }

    @PutMapping
    public ResponseEntity<AdmLoginResponseDto> updateAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        return ResponseEntity.ok(admLoginService.updateAdmLogin(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmLoginResponseDto> findAdmLoginById(@PathVariable Long id){
        return ResponseEntity.ok(admLoginService.findAdmLoginById(id));
    }

    @GetMapping("/by-person/{personId}")
    public ResponseEntity<List<AdmLoginResponseDto>> findAdmLoginByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok(admLoginService.findByPersonId(personId));
    }
    
    @PostMapping("/active")
    public ResponseEntity<AdmLoginResponseDto> setActiveAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        AdmLoginResponseDto response =  admLoginService.setActiveAdmLogin(requestDto);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/force-alter-passwd")
    public ResponseEntity<AdmLoginResponseDto> forceAlterPasswdAdmLogin(@RequestBody AdmLoginRequestDto requestDto){
        AdmLoginResponseDto response = admLoginService.setForceAlterPasswd(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/alter-passwd")
    public ResponseEntity<AdmLoginResponseDto> alterPasswd(@RequestBody AdmLoginRequestDto requestDto){
        AdmLoginResponseDto response = admLoginService.alterPasswd(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AdmLoginResponseDto> login(@RequestBody AdmLoginRequestDto requestDto){
        AdmLoginResponseDto response = admLoginService.login(requestDto);
        return ResponseEntity.ok(response);
    }    

    @PostMapping("/confirmation")
    public ResponseEntity<AdmLoginResponseDto> setConfirmedAt(@RequestBody AdmLoginRequestDto requestDto){
        AdmLoginResponseDto response = admLoginService.setConfirmedAt(requestDto);
        return ResponseEntity.ok(response);
    }  

    @PostMapping("/gen-token")
    public ResponseEntity<AdmLoginResponseDto> generateTokenConfirmation(@RequestBody AdmLoginRequestDto requestDto) {
        return ResponseEntity.ok(admLoginService.generateTokenConfirmation(requestDto));
    }

    
}
