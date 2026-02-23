package com.tribosoftec.easycon_api.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tribosoftec.easycon_api.domain.AdmLogin;
import com.tribosoftec.easycon_api.domain.dtos.requests.AdmLoginRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.AdmLoginResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonResponseDto;
import com.tribosoftec.easycon_api.exception.BusinessException;
import com.tribosoftec.easycon_api.repositories.AdmLoginRepositrory;
import com.tribosoftec.easycon_api.security.AuthService;
import com.tribosoftec.easycon_api.utils.Utils;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private Utils utils;

    private AdmLogin validationLogin(String email, Long id){
        AdmLogin admLogin = id==null ? this.findByEmail(email) : this.findById(id);
        if (admLogin == null){
            throw new BusinessException(
                HttpStatus.NOT_FOUND,
                "Registro de Login não localizado."
            );
        }
        return admLogin;
    }

    private void validateToken(String tkRequest, String tkBco, String context){
        if(!passwordEncoder.matches(tkRequest, tkBco)){
            throw new BusinessException(
                    HttpStatus.UNPROCESSABLE_CONTENT,
                    context + " inválida."
            );
        }        
    }

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
        responseDto.setConfirmed_at(admLogin.getConfirmedAt());
        responseDto.setToken(admLogin.getToken());
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
        AdmLogin admLogin = this.findById(id);
        if(admLogin == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, "Nenhum registro localizado para o id " + id);
        }
        return getLogin(admLogin);
    }

    public AdmLogin findById(Long id){
        return admLoginRepositrory.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Nenhum registro localizado para o id " + id));
    }

    public AdmLoginResponseDto createAdmLogin(AdmLoginRequestDto request){
        AdmLogin admLogin = new AdmLogin();
        admLogin.setPerson(personService.findById(request.getPerson_id()));
        admLogin.setName(request.getName());
        admLogin.setEmail(request.getEmail());
        admLogin.setPasswd(passwordEncoder.encode(request.getPasswd()));
        admLogin.setAlter_passwd(true);
        admLogin.setActive(true);
        var token = Utils.generateSimpleToken(6);
        admLogin.setToken(passwordEncoder.encode(token));
        admLogin = admLoginRepositrory.save(admLogin);

        // Enviando e-mail com código de autorização/confirmação
        Map<String,Object> params = Map.of(
            "name", admLogin.getName().split(" ")[0],
            "token", token
        );
        emailService.sendHtmlEmail(admLogin.getEmail(), "Cadastro Easycon", "register", params);
                
        return this.getLogin(admLogin); 
    }

    public AdmLoginResponseDto updateAdmLogin(AdmLoginRequestDto request){        
        AdmLogin admLogin = new AdmLogin();
        admLogin.setId(request.getId());
        admLogin.setPerson(personService.findById(request.getPerson_id()));
        admLogin.setName(request.getName());
        admLogin.setEmail(request.getEmail());
        admLogin = admLoginRepositrory.save(admLogin);
        return this.getLogin(admLogin);  
    }

    public AdmLoginResponseDto setActiveAdmLogin(AdmLoginRequestDto requestDto){
        AdmLogin admLogin = this.findById(requestDto.getId());
        admLogin.setActive(requestDto.getActive());
        admLogin = admLoginRepositrory.save(admLogin);
        return this.getLogin(admLogin);            
    }

    public AdmLoginResponseDto setForceAlterPasswd(AdmLoginRequestDto requestDto){
        AdmLogin admLogin = this.findById(requestDto.getId());
        admLogin.setAlter_passwd(requestDto.getAlter_passwd());
        admLogin = admLoginRepositrory.save(admLogin);
        return this.getLogin(admLogin);    
    }

    public AdmLoginResponseDto alterPasswd(AdmLoginRequestDto requestDto){
            AdmLogin admLogin = this.validationLogin(requestDto.getEmail(), null);
            this.validateToken(requestDto.getToken(), admLogin.getToken(), "Token");
            admLogin.setPasswd(passwordEncoder.encode(requestDto.getPasswd()));
            admLogin = admLoginRepositrory.save(admLogin);
            return this.getLogin(admLogin);          
    }    

    public AdmLogin findByEmail(String email){
        return admLoginRepositrory.findByEmail(email);
    }

    public AdmLoginResponseDto login(AdmLoginRequestDto requestDto){
        AdmLogin admLogin = this.validationLogin(requestDto.getEmail(), null);
        this.validateToken(requestDto.getPasswd(), admLogin.getPasswd(), "Senha");
        /*if(!passwordEncoder.matches(requestDto.getPasswd(), admLogin.getPasswd())){
            throw new BusinessException(
                    HttpStatus.UNPROCESSABLE_CONTENT,
                    "Senha inválida."
            );
        }*/
        AdmLoginResponseDto login = getLogin(admLogin);
        login.setToken(authService.login(login.getEmail()));
        return login;
    }   

    public List<AdmLoginResponseDto> findByPersonId(Long personId){
        try{
            List<AdmLogin> admLogins = admLoginRepositrory.findByPersonId(personId);
            return this.getLoginList(admLogins);
        } catch (Exception e) {
            throw new RuntimeException("Error find login by person id: " + e.getMessage());
        }
    }

    public AdmLoginResponseDto setConfirmedAt(AdmLoginRequestDto requestDto){    
        AdmLogin admLogin = this.validationLogin(requestDto.getEmail(), null);
        this.validateToken(requestDto.getToken(), admLogin.getToken(), "Token");
        admLogin.setConfirmedAt(Timestamp.from(Instant.now()));
        admLogin = admLoginRepositrory.save(admLogin);
        return getLogin(admLogin);
    }

    public AdmLoginResponseDto generateTokenConfirmation(AdmLoginRequestDto requestDto){    
        AdmLogin admLogin = this.validationLogin(requestDto.getEmail(), null);

        var token = Utils.generateSimpleToken(6);
        admLogin.setToken(passwordEncoder.encode(token));
        admLogin = admLoginRepositrory.save(admLogin);

        // Enviando e-mail com código de autorização/confirmação
        Map<String,Object> params = Map.of(
            "name", admLogin.getName().split(" ")[0],
            "token", token
        );
        emailService.sendHtmlEmail(admLogin.getEmail(), "Novo token solicitado " + token, "gentoken", params);
                
        return this.getLogin(admLogin); 
    }
    
}
