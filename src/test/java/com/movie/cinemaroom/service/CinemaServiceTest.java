package com.movie.cinemaroom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.exception.CinemaNotFoundException;
import com.movie.cinemaroom.model.Cinema;
import com.movie.cinemaroom.repository.CinemaRepository;
import com.movie.cinemaroom.service.impl.CinemaServiceImpl;

@ExtendWith(SpringExtension.class)
public class CinemaServiceTest {

	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private CinemaRepository cinemaRepository = Mockito.mock(CinemaRepository.class);
	
	@InjectMocks
	private CinemaServiceImpl cinemaService;
	
	private CinemaDto cinemaDto;
	private Cinema cinema;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	void before() throws ParseException {
		this.cinemaDto = new CinemaDto(25, 20, new HashSet<>());
		this.cinemaDto.setScreenId("b0dfed29-46db-4930-a68a-e9449aff9e37");
		this.cinema = modelMapper.map(this.cinemaDto, Cinema.class);
		when(cinemaRepository.save(any())).thenReturn(this.cinema);
		
		Optional<Cinema> cinemaOpt = Optional.of(this.cinema);
		when(cinemaRepository.findById("b0dfed29-46db-4930-a68a-e9449aff9e37")).thenReturn(cinemaOpt);
		
		List<Cinema> cinemaList = new ArrayList<>();
		cinemaList.add(this.cinema);
		when(cinemaRepository.findAll()).thenReturn(cinemaList);	
	}
	
	@Test
	@DisplayName("Test save cinema, expected success")
	void saveCinema_success() throws ParseException { 
		CinemaDto cinemaDto = cinemaService.save(this.cinemaDto);
		assertNotNull(cinemaDto);
		verify(cinemaRepository, times(1)).save(this.cinema);
	}
	
	
	@Test
	@DisplayName("Test update cinema, expected success")
	void updateCinema_success() throws ParseException {
		Cinema cinema = modelMapper.map(this.cinemaDto, Cinema.class);
		cinema.setMaxSeats(30);
		CinemaDto cinemaDto = modelMapper.map(cinema, CinemaDto.class);
		cinemaDto = cinemaService.update(cinemaDto);
		assertNotEquals(this.cinemaDto.getMaxSeats(), cinemaDto.getMaxSeats());
		cinema = cinemaRepository.save(cinema);
		assertNotNull(cinema);
	}
	
	@Test
	@DisplayName("Test update cinema, expected failed")
	void updateCinema_failed() throws ParseException {
		CinemaDto cinemaDto = new CinemaDto();
		cinemaDto.setScreenId("invalid_id");
		CinemaNotFoundException exception = assertThrows(CinemaNotFoundException.class, 
				() -> cinemaService.update(cinemaDto), "CinemaNotFoundException is expected.");
	
		assertEquals("Cinema with screen id: invalid_id is not found.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test delete cinema, expected success")
	void deleteCinema_success() throws ParseException {
		cinemaService.delete("b0dfed29-46db-4930-a68a-e9449aff9e37");
		Optional<Cinema> cinemaOpt = cinemaRepository.findById("b0dfed29-46db-4930-a68a-e9449aff9e37");
		assertNotNull(cinemaOpt.get());
		Cinema cinema = cinemaOpt.get();
		assertEquals(cinema.isActive(), false);
	}
	
	@Test
	@DisplayName("Test update cinema, expected failed")
	void deleteCinema_failed() throws ParseException {
		CinemaNotFoundException exception = assertThrows(CinemaNotFoundException.class, 
				() -> cinemaService.delete("invalid_id"), "CinemaNotFoundException is expected.");
	
		assertEquals("Cinema with screen id: invalid_id is not found.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test find all cinema, expected success")
	void findAllCinemas_success() {
		List<CinemaDto> cinemaList = cinemaService.findAll();
		assertNotNull(cinemaList);
		assertEquals(1, cinemaList.size());
		assertNotEquals(0, cinemaList.size());
		verify(cinemaRepository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Test find by id, expected success")
	void findById_success() {
		CinemaDto cinemaDto = cinemaService.findById("b0dfed29-46db-4930-a68a-e9449aff9e37");
		assertNotNull(cinemaDto);
		verify(cinemaRepository, times(1)).findById("b0dfed29-46db-4930-a68a-e9449aff9e37");
	}
	
	@Test
	@DisplayName("Test find by id, expected failed")
	void findById_failed() {
		CinemaNotFoundException exception = assertThrows(CinemaNotFoundException.class, 
				() -> cinemaService.findById("invalid_id"), "CinemaNotFoundException is expected.");
	
		assertEquals("Cinema with screen id: invalid_id is not found.", exception.getMessage());
	}
}
