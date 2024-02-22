package com.ryanisaig.tests.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryanisaig.tests.api.dto.ContactDetailsDTO;
import com.ryanisaig.tests.repository.entity.ContactDetailsEntity;
import com.ryanisaig.tests.service.ContactDetailsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactDetailsRestAPI {
	
	private final ContactDetailsService contactDetailsService;

	@GetMapping("/{id}")
	public ResponseEntity<ContactDetailsDTO> retrieveContactDetails(@PathVariable @NotNull(message = "Id is required") Long id) {
		return ResponseEntity.ok(mapToDto(contactDetailsService.getContactDetails(id)));
	}
	
	@PostMapping
	public ResponseEntity<Long> recordContactDetails(@RequestBody @Valid ContactDetailsDTO contactDetails) {
		return ResponseEntity.ok(contactDetailsService.saveContactDetails(contactDetails));
	}
	
	private ContactDetailsDTO mapToDto(ContactDetailsEntity entity) {
		if(entity == null) {
			return null;
		}
		
		return ContactDetailsDTO.builder().name(entity.getName()).phoneNumber(entity.getPhoneNumber()).build();
	}
}
