package com.movie.cinemaroom.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.service.impl.MovieServiceImpl;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieServiceImpl movieService;
	
	private MovieDto movieDto;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	public void before() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-06-29");
		Date endDate = formatter.parse("2022-08-29");
		this.movieDto = new MovieDto("Minions: The Rise of Gru", 87, "SU", 
				"Illumination entertainment", startDate, endDate);
		this.movieDto.setId("56r48865-e1f1-4bc2-9e2f-a32cda343d08");
		when(movieService.findById("56r48865-e1f1-4bc2-9e2f-a32cda343d08")).thenReturn(movieDto);
	}
	
	@Test
	@DisplayName("Test save movie, expected success")
	public void searchMovie_success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = objectWriter.writeValueAsString(this.movieDto);
	    
		mockMvc.perform(post("/movie")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andExpect(content().string("Movie is created"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test update movie, expected success")
	public void updateMovie_success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = objectWriter.writeValueAsString(this.movieDto);
	    
		mockMvc.perform(post("/movie/update")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Movie is updated"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test delete movie, expected success")
	public void deleteMovie_success() throws Exception {
		mockMvc.perform(delete("/movie/56r48865-e1f1-4bc2-9e2f-a32cda343d08")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().string("Movie is deleted"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find by id movie, expected success")
	public void getMovieById_success() throws Exception {
		mockMvc.perform(get("/movie/56r48865-e1f1-4bc2-9e2f-a32cda343d08")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().json("{\r\n"
				+ "    \"id\": \"56r48865-e1f1-4bc2-9e2f-a32cda343d08\",\r\n"
				+ "    \"title\": \"Minions: The Rise of Gru\",\r\n"
				+ "    \"runTime\": 87,\r\n"
				+ "    \"rating\": \"SU\",\r\n"
				+ "    \"productionCompany\": \"Illumination entertainment\",\r\n"
				+ "    \"startDate\": \"2022-06-29\",\r\n"
				+ "    \"endDate\": \"2022-08-29\",\r\n"
				+ "    \"active\": true\r\n"
				+ "}"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test find by id movie, expected success")
	public void getAllMovies_success() throws Exception {
		mockMvc.perform(get("/movie")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
