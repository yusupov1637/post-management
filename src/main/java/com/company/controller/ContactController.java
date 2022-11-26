package com.company.controller;

import com.company.dto.ContactDTO;
import com.company.entity.Contact;
import com.company.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.createContact(contactDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Contact>> getList(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        return ResponseEntity.ok(contactService.getAllContact(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            contactService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        return contactService.update(id, contactDTO);
    }
}
