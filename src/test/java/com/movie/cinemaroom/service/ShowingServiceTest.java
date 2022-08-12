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

import com.movie.cinemaroom.dto.ShowingDto;
import com.movie.cinemaroom.exception.ShowingNotFoundException;
import com.movie.cinemaroom.model.Showing;
import com.movie.cinemaroom.repository.ShowingRepository;
import com.movie.cinemaroom.service.impl.ShowingServiceImpl;

@ExtendWith(SpringExtension.class)
public class ShowingServiceTest {

	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private ShowingRepository showingRepository = Mockito.mock(ShowingRepository.class);
	
	@InjectMocks
	private ShowingServiceImpl showingService;
	
	private ShowingDto showingDto;
	private Showing showing;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	void before() throws ParseException {
		this.showingDto = new ShowingDto(10000, 10);
		this.showingDto.setShowingId("90d48880-j1f1-4bc2-9e2f-a32cda343c08");
		this.showing = modelMapper.map(showingDto, Showing.class);
		
		Optional<Showing> showingOpt = Optional.of(this.showing);
		when(showingRepository.findById("90d48880-j1f1-4bc2-9e2f-a32cda343c08")).thenReturn(showingOpt);
		when(showingRepository.save(any())).thenReturn(this.showing);
		
		List<Showing> showingList = new ArrayList<>();
		showingList.add(this.showing);
		when(showingRepository.findAll()).thenReturn(showingList);
	}
	
	@Test
	@DisplayName("Test save showing, expected success")
	void saveShowing_success() throws ParseException {
		ShowingDto showingDto = showingService.save(this.showingDto);
		assertNotNull(showingDto);
		
		this.showing.setActive(true);
		verify(showingRepository, times(1)).save(this.showing);
	}
	
	@Test
	@DisplayName("Test update showing, expected success")
	void updateShowing_success() throws ParseException {
		Showing showing = modelMapper.map(showingDto, Showing.class);
		this.showingDto.setNumSeats(11);
		ShowingDto showingDto = showingService.update(this.showingDto);
		assertNotEquals(showingDto.getNumSeats(), showing.getNumSeats());
		showing.setNumSeats(11);
		showing = showingRepository.save(showing);
		assertEquals(showingDto.getNumSeats(), showing.getNumSeats());
	}
	
	@Test
	@DisplayName("Test update showing, expected failed")
	void updateShowing_failed() throws ParseException {
		ShowingDto showingDto = new ShowingDto();
		showingDto.setShowingId("invalid_id");
		
		ShowingNotFoundException exception = assertThrows(ShowingNotFoundException.class, 
				() -> showingService.update(showingDto), "ShowingNotFoundException is expected");
	
		assertEquals("Showing id: invalid_id is not found.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test delete showing, expected success")
	void deleteShowing_success() throws ParseException {
		showingService.delete("90d48880-j1f1-4bc2-9e2f-a32cda343c08");
	}
	
	@Test
	@DisplayName("Test delete showing, expected failed")
	void deleteShowing_failed() throws ParseException {
		Optional<Showing> showingOpt = Optional.empty();
		when(showingRepository.findById("invalid_id")).thenReturn(showingOpt);
		
		ShowingNotFoundException exception = assertThrows(ShowingNotFoundException.class, 
				() -> showingService.findById("invalid_id"), "ShowingNotFoundException is expected");
	
		assertEquals("Showing id: invalid_id is not found.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test find by id, expected success")
	void findById_success() {
		ShowingDto showingDto = showingService.findById("90d48880-j1f1-4bc2-9e2f-a32cda343c08");
		assertNotNull(showingDto);
		assertEquals(this.showingDto.getStartTime(), showingDto.getStartTime());
		verify(showingRepository, times(1)).findById("90d48880-j1f1-4bc2-9e2f-a32cda343c08");
	}
	
	@Test
	@DisplayName("Test find by id, expected failed")
	void findById_failed() {
		ShowingNotFoundException exception = assertThrows(ShowingNotFoundException.class, 
				() -> showingService.findById("invalid_id"), "ShowingNotFoundException is expected");
	
		assertEquals("Showing id: invalid_id is not found.", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test find all showings, expected success")
	void findAllShowings_success() {
		List<ShowingDto> showingList = showingService.findAll();
		assertNotNull(showingList);
		assertEquals(1, showingList.size());
		assertNotEquals(0, showingList.size());
		verify(showingRepository, times(1)).findAll();
	}
}
