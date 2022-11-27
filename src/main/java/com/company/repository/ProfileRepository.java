package com.company.repository;

import com.company.entity.Contact;
import com.company.entity.Profile;
import com.company.mapper.ProfileMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query("from Contact inner join Profile p where p.id=:pid")
    Contact findContactByProfileId(@Param("pid") Long pid);


   // @Query("from Profile inner join Contact c where c.id=:cId")
    Profile findByContact_Id(Long contact_id);


    @Query("select new com.company.mapper.ProfileMapper(p.id,p.name,c.phoneNum) from Profile p inner join Contact c where p.id=:pId")
    List<ProfileMapper> findNameAndPhone(@Param("pId") Long id);


    @Query("select new com.company.mapper.ProfileMapper(p.id,p.name,c.phoneNum) from Profile p inner join Contact c where c.id=:cId")
    List<ProfileMapper> findByContactId(@Param("cId") Long contactId);
}
