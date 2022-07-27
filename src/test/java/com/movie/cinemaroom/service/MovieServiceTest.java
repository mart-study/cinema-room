package com.movie.cinemaroom.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
