package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.Condm;
import com.tribosoftec.easycon_api.domain.dtos.requests.CondmRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.CondmResponseDto;
import com.tribosoftec.easycon_api.repositories.CondmRepository;

@Service
public class CondmService {

    @Autowired
    private PersonService personService;

    @Autowired
    private CondmRepository condmRepository;


    public Condm findById(Long id){
        return condmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condominium not found with ID: " + id));
    }

    public CondmResponseDto getCondm(Condm condm) {

        try {
            if (condm == null) {
                return null;
            }
            CondmResponseDto responseDto = new CondmResponseDto();
            responseDto.setPerson(personService.findPersonById(condm.getPerson().getId()));
            responseDto.setId(condm.getId());
            responseDto.setName(condm.getName());
            responseDto.setDescription(condm.getDescription());
            responseDto.setCep(condm.getCep());
            responseDto.setStreet(condm.getStreet());
            responseDto.setNumber(condm.getNumber());
            responseDto.setComplement(condm.getComplement());
            responseDto.setActive(condm.getActive());
            responseDto.setCreated_at(condm.getCreatedAt());
            responseDto.setUpdated_at(condm.getUpdatedAt());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching condominium data", e);
        }
    }

    private Condm setCondm(CondmRequestDto requestDto) {
        Condm condm = new Condm();
        if(requestDto.getId() != null){
            condm.setId(requestDto.getId());
        }
        condm.setPerson(personService.findById(requestDto.getPerson_id()));
        condm.setName(requestDto.getName());
        condm.setDescription(requestDto.getDescription());
        condm.setCep(requestDto.getCep());
        condm.setStreet(requestDto.getStreet());
        condm.setNumber(requestDto.getNumber());
        condm.setComplement(requestDto.getComplement());
        condm.setActive(requestDto.getActive());
        return condm;
    }

    public CondmResponseDto createCondm(CondmRequestDto requestDto) {
        try {
            Condm savedCondm = condmRepository.save(this.setCondm(requestDto));
            return this.getCondm(savedCondm);
        } catch (Exception e) {
            throw new RuntimeException("Error creating condominium", e);
        }
    }    


    public CondmResponseDto updateCondm(CondmRequestDto requestDto) {
        try {
            Condm updatedCondm = condmRepository.save(this.setCondm(requestDto));
            return this.getCondm(updatedCondm);
        } catch (Exception e) {
            throw new RuntimeException("Error updating condominium", e);
        }
    }

    public CondmResponseDto deleteCondm(Long id) {
        try {
            Condm condm = this.findById(id);
            condm.setActive(false);
            Condm deletedCondm = condmRepository.save(condm);
            return getCondm(deletedCondm);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting condominium", e);
        }
    }

    public CondmResponseDto setActiveCondm(CondmRequestDto requestDto) {
        try {
            if(requestDto.getActive()){
                return activateCondm(requestDto.getId());
            } else {
                return inactivateCondm(requestDto.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating condominium active status", e);
        }
    }

    public CondmResponseDto activateCondm(Long id) {
        try {
            Condm condm = this.findById(id);
            condm.setActive(true);
            Condm activatedCondm = condmRepository.save(condm);
            return getCondm(activatedCondm);
        } catch (Exception e) {
            throw new RuntimeException("Error activating condominium", e);
        }
    }

    public CondmResponseDto inactivateCondm(Long id) {
        try {
            Condm condm = this.findById(id);
            condm.setActive(false);
            Condm inactivatedCondm = condmRepository.save(condm);
            return getCondm(inactivatedCondm);
        } catch (Exception e) {
            throw new RuntimeException("Error inactivating condominium", e);
        }
    }

    public List<CondmResponseDto> findAll() {
        try {
            List<Condm> condms = condmRepository.findAll();
            return condms.stream().map(this::getCondm).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching condominiums", e);
        }
    }

}
