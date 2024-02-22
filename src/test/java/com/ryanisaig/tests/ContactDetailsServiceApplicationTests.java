package com.ryanisaig.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = ContactDetailsServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class ContactDetailsServiceApplicationTests {

	@Autowired
    private MockMvc mvc;
	
	@Test void whenPostRequestIsValid_mustReturn_200() throws Exception{
		 mvc.perform(post("/contacts")
				  .content("{\"name\": \"Ryan\", \"phoneNumber\": \"12345\"}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	@Test void whenPostRequestIsMissing_mustReturn_400() throws Exception{
		 mvc.perform(post("/contacts")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}
	
	@Test void whenPostRequestIsMissingName_mustReturn_400() throws Exception{
		 mvc.perform(post("/contacts")
				  .content("{\"name\": \"\", \"phoneNumber\": \"12345\"}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$[0]", is("Name is required")));
	}
	
	@Test void whenPostRequestIsMissingPhoneNumber_mustReturn_400() throws Exception{
		 mvc.perform(post("/contacts")
				  .content("{\"name\": \"Ryan\", \"phoneNumber\": \"\"}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$[0]", is("Phone number is required")));
	}
	
	@Test void whenGetRequestIsValid_mustReturn_200() throws Exception{
		 mvc.perform(post("/contacts")
				  .content("{\"name\": \"Ryan\", \"phoneNumber\": \"12345\"}")
			      .contentType(MediaType.APPLICATION_JSON));
		
		 mvc.perform(get("/contacts/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.name", is("Ryan")))
			      .andExpect(jsonPath("$.phoneNumber", is("12345")));
	}
	
	@Test void whenGetRequestIdIsNotfound_mustReturn_404() throws Exception{
		 mvc.perform(get("/contacts/99")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
	}
}
