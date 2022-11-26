package com.company.service;

import com.company.dto.ContactDTO;
import com.company.entity.Contact;
import com.company.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<?> createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setPhoneNum(contactDTO.getPhoneNum());
        contactRepository.save(contact);
        return ResponseEntity.ok().build();
    }

    public Page<Contact> getAllContact(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Contact> pageObj = contactRepository.findAll(paging);

        List<Contact> list = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();


        Page<Contact> response = new PageImpl(list, paging, totalElements);
        // return new EmployeePageResponseDTO(dtoList, totalElements);
        return response;

    }
    public void deleteById(Long id){
        contactRepository.deleteById(id);
    }
    public ResponseEntity<?> getById(Long id){
        Optional<Contact> byId = contactRepository.findById(id);
        if (!byId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Contact contact = byId.get();
        ContactDTO contactDTO=new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setPhoneNum(contact.getPhoneNum());
        return ResponseEntity.ok(contactDTO);
    }
    public ResponseEntity<?> update(Long id,ContactDTO contactDTO){
        Optional<Contact> byId = contactRepository.findById(id);
        if (!byId.isPresent()){
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
        }
        Contact contact = byId.get();
        contact.setId(id);
        contact.setPhoneNum(contactDTO.getPhoneNum());

        contactRepository.save(contact);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
