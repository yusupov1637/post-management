package com.company.controller;

import com.company.dto.ContactDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Contact;
import com.company.entity.Profile;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.createProfile(profileDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Profile>> getList(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        return ResponseEntity.ok(profileService.getAllProfile(page, size));
    }
}
