package com.tribosoftec.easycon_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tribosoftec.easycon_api.domain.Person;
import com.tribosoftec.easycon_api.domain.Profile;
import com.tribosoftec.easycon_api.domain.dtos.requests.ProfileRequestDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.PersonResponseDto;
import com.tribosoftec.easycon_api.domain.dtos.responses.ProfileResponseDto;
import com.tribosoftec.easycon_api.repositories.PersonRepository;
import com.tribosoftec.easycon_api.repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonRepository personRepository;

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDto getProfile(Profile profile) {
        PersonResponseDto personDto = personService.getPerson(profile.getPerson());
        return new ProfileResponseDto(profile.getId(), 
                                      profile.getName(), 
                                      profile.getDescription(), 
                                      profile.getActive(), 
                                      profile.getCreatedAt(), 
                                      profile.getUpdatedAt(), 
                                      personDto);
    }

    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {
        try {
            Profile profile = new Profile();
            profile.setName(profileRequestDto.getName());
            profile.setDescription(profileRequestDto.getDescription());
            profile.setActive(profileRequestDto.isActive());
            Person person = personRepository.findById(profileRequestDto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + profileRequestDto.getPersonId()));
            profile.setPerson(person);
            Profile profile2 = profileRepository.save(profile);
            return this.getProfile(profile2);
        } catch (Exception e) {
            throw new RuntimeException("Error creating profile", e);
        }

    }

    public Profile findById(Long id) {
        try {
            return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching profile: " + e.getMessage(), e);
        }
    }

    public ProfileResponseDto findProfileById(Long id) {
        try {
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id));
            return this.getProfile(profile);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving profile by ID", e);
        }
    }

    public ProfileResponseDto delete(Long id) {
        try {
            if (!profileRepository.existsById(id)) {
                throw new RuntimeException("Profile not found with ID: " + id);
            }
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id));
            ProfileResponseDto responseDto = this.getProfile(profile);
            profileRepository.deleteById(id);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting profile", e);
        }
    }

    public ProfileResponseDto update(ProfileRequestDto profileRequestDto) {
        try {
            Profile existingProfile = profileRepository.findById(profileRequestDto.getId())
                    .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileRequestDto.getId()));
            existingProfile.setName(profileRequestDto.getName());
            existingProfile.setDescription(profileRequestDto.getDescription());
            existingProfile.setActive(profileRequestDto.isActive());
            Person person = personRepository.findById(profileRequestDto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + profileRequestDto.getPersonId()));
            existingProfile.setPerson(person);
            Profile updatedProfile = profileRepository.save(existingProfile);
            return this.getProfile(updatedProfile);
        } catch (Exception e) {
            throw new RuntimeException("Error updating profile", e);
        }
    }

    public List<ProfileResponseDto> findProfileByPersonId(Long personId) {
        try {
            List<Profile> profiles = profileRepository.findProfileByPersonId(personId);
            return profiles.stream()
                    .map(this::getProfile)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all profiles", e);
        }
    }

}        