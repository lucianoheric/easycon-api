package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.AdmLogin;
import com.tribosoftec.easycon_api.domain.dtos.requests.AdmLoginRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.AdmLoginResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonResponseDto;
import com.tribosoftec.easycon_api.repositories.AdmLoginRepositrory;
import com.tribosoftec.easycon_api.security.AuthService;

@Service
public class AdmLoginService {

    @Autowired
    private PersonService personService;

    @Autowired
    private AdmLoginRepositrory admLoginRepositrory;

    @Autowired
    private PasswordEncoder passwordEncoder;  
    
    @Autowired
    private AuthService authService;

    public AdmLoginResponseDto getLogin(AdmLogin admLogin){
        AdmLoginResponseDto responseDto = new AdmLoginResponseDto();
        responseDto.setId(admLogin.getId());
        PersonResponseDto person = personService.findPersonById(admLogin.getPerson().getId());
        responseDto.setPerson(person);
        responseDto.setName(admLogin.getName());
        responseDto.setEmail(admLogin.getEmail());
        responseDto.setPasswd(admLogin.getPasswd());
        responseDto.setAlter_passwd(admLogin.getAlter_passwd());
        responseDto.setActive(admLogin.getActive());
        responseDto.setCreated_at(admLogin.getCreatedAt());
        responseDto.setUpdated_at(admLogin.getUpdatedAt());
        return responseDto;
    }


    public List<AdmLoginResponseDto> getLoginList(List<AdmLogin> admLogin){
        try {
            List<AdmLoginResponseDto> responseDto =
                admLogin.stream()
                        .map(this::getLogin)
                        .collect(Collectors.toList());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error creating login List." + e.getMessage());
        }
    }    

    public AdmLoginResponseDto findAdmLoginById(Long id){
        try {
            AdmLogin admLogin = this.findById(id);
            return getLogin(admLogin);
        } catch (Exception e) {
            throw new RuntimeException("Error find login by id: "+ id + ". Error:" + e.getMessage());
        }
        
    }

    public AdmLogin findById(Long id){
        return admLoginRepositrory.findById(id)
                .orElseThrow(() -> new RuntimeException("Login not found with ID: " + id));
    }

    public AdmLoginResponseDto createAdmLogin(AdmLoginRequestDto request){
        try {            
            AdmLogin admLogin = new AdmLogin();
            admLogin.setPerson(personService.findById(request.getPerson_id()));
            admLogin.setName(request.getName());
            admLogin.setEmail(request.getEmail());
            admLogin.setPasswd(passwordEncoder.encode(request.getPasswd()));
            admLogin.setAlter_passwd(true);
            admLogin.setActive(false);
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);  
        } catch (Exception e) {
            throw new RuntimeException("Error creating Login: " + e.getMessage());
        }
    }

    public AdmLoginResponseDto updateAdmLogin(AdmLoginRequestDto request){
        try {            
            AdmLogin admLogin = new AdmLogin();
            admLogin.setId(request.getId());
            admLogin.setPerson(personService.findById(request.getPerson_id()));
            admLogin.setName(request.getName());
            admLogin.setEmail(request.getEmail());
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);  
        } catch (Exception e) {
            throw new RuntimeException("Error updating Login: " + e.getMessage());
        }
    }

    public AdmLoginResponseDto setActiveAdmLogin(AdmLoginRequestDto requestDto){
        try {
            AdmLogin admLogin = this.findById(requestDto.getId());
            admLogin.setActive(requestDto.getActive());
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);            
        } catch (Exception e) {
            throw new RuntimeException("Error set active yes/no Login: " + e.getMessage());
        }      
    }

    public AdmLoginResponseDto setForceAlterPasswd(AdmLoginRequestDto requestDto){
        try {
            AdmLogin admLogin = this.findById(requestDto.getId());
            admLogin.setAlter_passwd(requestDto.getAlter_passwd());
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);            
        } catch (Exception e) {
            throw new RuntimeException("Error set force alter passwd yes/no Login: " + e.getMessage());
        }      
    }

    public AdmLoginResponseDto alterPasswd(AdmLoginRequestDto requestDto){
        try {
            AdmLogin admLogin = this.findById(requestDto.getId());
            admLogin.setPasswd(passwordEncoder.encode(requestDto.getPasswd()));
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);            
        } catch (Exception e) {
            throw new RuntimeException("Error set force alter passwd yes/no Login: " + e.getMessage());
        }      
    }    

    public AdmLogin findByEmail(String email){
        return admLoginRepositrory.findByEmail(email);
    }

    public AdmLoginResponseDto login(AdmLoginRequestDto requestDto){
        try {
            AdmLogin admLogin = this.findByEmail(requestDto.getEmail());
            if(admLogin == null){
                throw new RuntimeException("Email " + requestDto.getEmail() + " not found.");
            }
            if(!passwordEncoder.matches(requestDto.getPasswd(), admLogin.getPasswd())){
                throw new RuntimeException("invalid password.");
            }
            AdmLoginResponseDto login = getLogin(admLogin);
            login.setToken(authService.login(login.getEmail()));
            return login;          
        } catch (Exception e) {
            throw new RuntimeException("Error Login: " + e.getMessage());
        }      
    }   

    public List<AdmLoginResponseDto> findByPersonId(Long personId){
        try{
            List<AdmLogin> admLogins = admLoginRepositrory.findByPersonId(personId);
            return this.getLoginList(admLogins);
        } catch (Exception e) {
            throw new RuntimeException("Error find login by person id: " + e.getMessage());
        }
    }

    
}
