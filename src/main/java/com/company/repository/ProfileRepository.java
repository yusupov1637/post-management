package com.company.repository;

import com.company.entity.Contact;
import com.company.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query("from Contact inner join Profile p where p.id=:pid")
    Contact findContactByProfileId(@Param("pid") Long pid);
}
