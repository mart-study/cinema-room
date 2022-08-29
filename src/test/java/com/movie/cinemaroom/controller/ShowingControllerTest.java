package com.movie.cinemaroom.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

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
import com.movie.cinemaroom.dto.ShowingDto;
import com.movie.cinemaroom.service.impl.ShowingServiceImpl;

@WebMvcTest(controllers = ShowingController.class)
public class ShowingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShowingServiceImpl showingService;
	
	private ShowingDto showingDto;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	public void before() throws ParseException {
		this.showingDto = new ShowingDto(100000, 10);
		this.showingDto.setShowingId("b0kled29-87db-4930-a68f-e9449ann9e37");
		when(showingService.findById("b0kled29-87db-4930-a68f-e9449ann9e37")).thenReturn(this.showingDto);
	}
	
	@Test
	@DisplayName("Test save showing, expected success")
	public void saveShowing_success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = objectWriter.writeValueAsString(this.showingDto);
	    
		mockMvc.perform(post("/showing")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andExpect(content().string("Showing is created"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test update showing, expected success")
	public void updateShowing_success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = objectWriter.writeValueAsString(this.showingDto);
	    
		mockMvc.perform(put("/showing")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Showing is updated"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test delete showing, expected success")
	public void deleteShowing_success() throws Exception {
		mockMvc.perform(delete("/showing/b0kled29-87db-4930-a68f-e9449ann9e37")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Showing is deleted"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find by id showing, expected success")
	public void getShowingId_success() throws Exception {
		mockMvc.perform(get("/showing/b0kled29-87db-4930-a68f-e9449ann9e37")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().json("{\r\n"
				+ "    \"showingId\": \"b0kled29-87db-4930-a68f-e9449ann9e37\",\r\n"
				+ "    \"startTime\": 100000,\r\n"
				+ "    \"numSeats\": 10,\r\n"
				+ "    \"movie\": null,\r\n"
				+ "    \"active\": true\r\n"
				+ "}"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find all showings, expected success")
	public void getAllShowings_success() throws Exception {
		mockMvc.perform(get("/showing")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
