package com.company;

import com.company.dto.ContactDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Contact;
import com.company.service.ContactService;
import com.company.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostManagementApplicationTests {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ProfileService profileService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveContact(){
        ContactDTO contactDTO =new ContactDTO();
        contactDTO.setPhoneNum("987899876");
contactService.createContact(contactDTO);
    }

    @Test
    void saveProfile(){
        ProfileDTO profileDTO=new ProfileDTO();
        profileDTO.setName("asad");
        profileDTO.setSurname("asadov");
        profileDTO.setContact(new Contact(1L,"2354543"));
        profileService.createProfile(profileDTO);
    }

    @Test
    void getcontacProfileId(){
        ContactDTO contactByProfileId = profileService.getContactByProfileId(1L);
        System.out.println(contactByProfileId);
    }

}
