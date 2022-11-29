package com.company.repository;

import com.company.entity.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ContactRepository extends JpaRepository<Contact,Long> {


    @Transactional
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update Contact set phoneNum=:phoneNum where id=:id")
    Contact updateContact(@Param("phoneNum") String phoneNum,@Param("id") Long id);
}
