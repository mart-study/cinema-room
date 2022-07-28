package com.movie.cinemaroom.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.exception.MovieNotFoundException;
import com.movie.cinemaroom.model.Movie;
import com.movie.cinemaroom.repository.MovieRepository;
import com.movie.cinemaroom.service.impl.MovieServiceImpl;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	void before() {
		
	}
	
	@Test
	@DisplayName("Test save movie, expeceted success")
	void saveMovie_success() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-06-29");
		Date endDate = formatter.parse("2022-08-29");
		MovieDto movieDto = new MovieDto("Minions: The Rise of Gru", 87, "SU", 
				"Illumination entertainment", startDate, endDate);
		
		Movie movie = modelMapper.map(movieDto, Movie.class);
		when(movieRepository.save(any())).thenReturn(movie);
		
		movieDto = movieService.save(movieDto);
		assertNotNull(movieDto);
		
		verify(movieRepository, times(1)).save(movie);
	}
	
	@Test
	@DisplayName("Test update movie, expeceted success")
	void updateMovie_success() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-06-29");
		Date endDate = formatter.parse("2022-08-29");
		MovieDto movieDto = new MovieDto("Minions: The Rise of Gru", 87, "SU", 
				"Illumination entertainment", startDate, endDate);
		movieDto.setId("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
		
		Movie movie = modelMapper.map(movieDto, Movie.class);
		Optional<Movie> movieOpt = Optional.of(movie);
		when(movieRepository.save(any())).thenReturn(movie);
		when(movieRepository.findById("48d48865-e1f1-4bc2-9e2f-a32cda343c08")).thenReturn(movieOpt);
		
		movieDto.setProductionCompany("Illumination Entertainment");
		movieDto = movieService.update(movieDto);
		assertNotNull(movieDto);
		assertEquals(movieDto.getProductionCompany(), movie.getProductionCompany());
		
		verify(movieRepository, times(1)).save(movie);
	}
	
	@Test
	@DisplayName("Test delete movie, expeceted success")
	void deleteMovie_success() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-06-29");
		Date endDate = formatter.parse("2022-08-29");
		MovieDto movieDto = new MovieDto("Minions: The Rise of Gru", 87, "SU", 
				"Illumination entertainment", startDate, endDate);
		movieDto.setId("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
		
		Movie movie = modelMapper.map(movieDto, Movie.class);
		Optional<Movie> movieOpt = Optional.of(movie);
		when(movieRepository.findById("48d48865-e1f1-4bc2-9e2f-a32cda343c08")).thenReturn(movieOpt);
		
		movieService.deleteMovie("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
	}
	
	@Test
	@DisplayName("Test delete movie, expeceted failed")
	void deleteMovie_failed() throws ParseException {
		Optional<Movie> movieOpt = Optional.empty();
		when(movieRepository.findById("58d48865-e1f1-4bc2-9e2f-a32cda343c05")).thenReturn(movieOpt);
		
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, 
				() -> movieService.deleteMovie("58d48865-e1f1-4bc2-9e2f-a32cda343c05"),
				"MovieNotFoundException is expected");
	
		Assertions.assertEquals("Movie with id: 58d48865-e1f1-4bc2-9e2f-a32cda343c05 is not found", exception.getMessage());
	}
}
