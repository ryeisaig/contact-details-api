package com.ryanisaig.tests.service.impl;

import org.springframework.stereotype.Service;

import com.ryanisaig.tests.api.dto.ContactDetailsDTO;
import com.ryanisaig.tests.api.exception.NotFoundException;
import com.ryanisaig.tests.repository.ContactDetailsRepository;
import com.ryanisaig.tests.repository.entity.ContactDetailsEntity;
import com.ryanisaig.tests.service.ContactDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {
	
	private final ContactDetailsRepository contactDetailsRepository;

	@Override
	public Long saveContactDetails(ContactDetailsDTO contactDetails) {
		log.info("Saving contact details: {}", contactDetails);
		
		ContactDetailsEntity entity = contactDetailsRepository.saveAndFlush(toEntity(contactDetails));
		
		log.info("Saved. Id = ", entity.getId());
		
		return entity.getId();
	}

	@Override
	public ContactDetailsEntity getContactDetails(Long id) {
		log.info("Retrieving contact details with requested Id: {}", id);

		ContactDetailsEntity result = contactDetailsRepository.findById(id).orElseThrow(() -> new NotFoundException("Requested id is not found"));
		
		log.info("Requested Id: {} is found", id);
		
		return result;
	}
	
	private ContactDetailsEntity toEntity(ContactDetailsDTO dto) {
		return ContactDetailsEntity.builder().name(dto.getName()).phoneNumber(dto.getPhoneNumber()).build();
	}

}
