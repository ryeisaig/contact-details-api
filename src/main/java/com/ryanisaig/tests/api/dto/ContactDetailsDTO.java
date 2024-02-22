package com.ryanisaig.tests.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactDetailsDTO {
	
	@NotEmpty(message = "Name is required")
	private String name;
    
	@NotEmpty(message = "Phone number is required")
	private String phoneNumber;	
}
