package com.ryanisaig.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryanisaig.tests.repository.entity.ContactDetailsEntity;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetailsEntity, Long> {

}
