package com.movie.cinemaroom.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.service.impl.CinemaServiceImpl;

@WebMvcTest(controllers = CinemaController.class)
public class CinemaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CinemaServiceImpl cinemaService;
	
	private CinemaDto cinemaDto;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	public void before() throws ParseException {
		this.cinemaDto = new CinemaDto(25, 20, new HashSet<>());
		this.cinemaDto.setScreenId("b0dfed29-46db-4930-a68a-e9449aff9e37");
		when(cinemaService.findById("b0dfed29-46db-4930-a68a-e9449aff9e37")).thenReturn(this.cinemaDto);
	}
	
	@Test
	@DisplayName("Test save cinema, expected success")
	public void saveCinema_success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = objectWriter.writeValueAsString(this.cinemaDto);
	    
		mockMvc.perform(put("/cinema")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Cinema is updated"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test delete cinema, expected success")
	public void deleteCinema_success() throws Exception {
		mockMvc.perform(delete("/cinema/b0dfed29-46db-4930-a68a-e9449aff9e37")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Cinema is deleted"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find by id cinema, expected success")
	public void getCinemaById_success() throws Exception {
		mockMvc.perform(get("/cinema/b0dfed29-46db-4930-a68a-e9449aff9e37")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().json("{\r\n"
				+ "    \"screenId\": \"b0dfed29-46db-4930-a68a-e9449aff9e37\",\r\n"
				+ "    \"maxSeats\": 25,\r\n"
				+ "    \"sreenSize\": 20,\r\n"
				+ "    \"active\": true\r\n"
				+ "}"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find all cinema, expected success")
	public void getAllCinemas_success() throws Exception {
		mockMvc.perform(get("/cinema")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
