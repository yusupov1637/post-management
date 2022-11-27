package com.company.service;

import com.company.dto.ContactDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Contact;
import com.company.entity.Profile;
import com.company.mapper.ProfileMapper;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    ContactService contactService;


    public ResponseEntity<?> createProfile(ProfileDTO profileDTO) {
//        Profile profile = new Profile();
//        profile.setName(profileDTO.getName());
//        profile.setSurname(profile.getSurname());
//        profile.setContact(profileDTO.getContact());
        Profile profile = dtoToEntity(profileDTO);
        profileRepository.save(profile);
        return ResponseEntity.ok().build();
    }

    public Page<Profile> getAllProfile(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Profile> pageObj = profileRepository.findAll(paging);

        List<Profile> list = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<ProfileDTO> listdto = new ArrayList<>();
        for (Profile profile : list) {
            listdto.add(entityDto(profile));
        }

        Page<Profile> response = new PageImpl(listdto, paging, totalElements);
        // return new EmployeePageResponseDTO(dtoList, totalElements);
        return response;

    }

    public ContactDTO getContactByProfileId(Long id) {
        Contact contactByProfileId = profileRepository.findContactByProfileId(id);
        ContactDTO contactDTO = contactService.entityDto(contactByProfileId);
        return contactDTO;
    }

    public ProfileDTO getProfileByContactId(Long contact_id) {
        Profile byContact_id = profileRepository.findByContact_Id(contact_id);
        ProfileDTO profileDTO = entityDto(byContact_id);
        return profileDTO;
    }
    public List<ProfileDTO> getProfileNAmeAndPhone(Long profile_id){
        List<ProfileMapper> nameAndPhone = profileRepository.findNameAndPhone(profile_id);
        List<ProfileDTO> profileDTOList=new ArrayList<>();
        nameAndPhone.forEach(entity->{
            ProfileDTO profileDTO=new ProfileDTO();
            profileDTO.setId(entity.getId());
            profileDTO.setName(entity.getName());
            Contact contact=new Contact();
            contact.setPhoneNum(entity.getPhone());
            profileDTO.setContact(contact);
            profileDTOList.add(profileDTO);
        });
        return profileDTOList;
    }


    public ProfileDTO entityDto(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setName(profile.getName());
        profileDTO.setSurname(profile.getSurname());
        profileDTO.setContact(profile.getContact());
        return profileDTO;
    }

    public Profile dtoToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setName(profileDTO.getName());
        profile.setSurname(profileDTO.getSurname());
        profile.setContact(profileDTO.getContact());
        return profile;
    }
}
