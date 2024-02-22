package com.ryanisaig.tests.service;

import com.ryanisaig.tests.api.dto.ContactDetailsDTO;
import com.ryanisaig.tests.repository.entity.ContactDetailsEntity;

public interface ContactDetailsService {
	
	/** Saves contact details to h2 in-memory database
	 * 
	 * @param name
	 * @param phoneNumber
	 * @return id
	 */
	public Long saveContactDetails(ContactDetailsDTO contactDetails);
	
	/** Retrieves contact details from h2 in-memory database using the id;
	 * 
	 * @param id
	 * @return ContactDetailsEntity
	 */
	public ContactDetailsEntity getContactDetails(Long id);
}	
