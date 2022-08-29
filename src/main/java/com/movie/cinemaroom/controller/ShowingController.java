package com.movie.cinemaroom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.cinemaroom.dto.PagingResultDto;
import com.movie.cinemaroom.dto.ShowingDto;
import com.movie.cinemaroom.service.ShowingService;

@RestController
@RequestMapping("/showing")
public class ShowingController {

	@Autowired
	private ShowingService showingService;
	
	/**
	 * Save new showing
	 * 
	 * @param showingDto
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> saveShowing(@Valid @RequestBody ShowingDto showingDto) { 
		showingService.save(showingDto);
		return new ResponseEntity<>("Showing is created", HttpStatus.CREATED);
	}
	
	/**
	 * Update existing showing
	 * 
	 * @param showingDto
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateShowing(@Valid @RequestBody ShowingDto showingDto) { 
		if (showingDto.getShowingId() == null || showingDto.getShowingId().isEmpty()) {
			return new ResponseEntity<>("Showing id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		
		showingService.update(showingDto);
		return new ResponseEntity<>("Showing is updated", HttpStatus.OK);
	}
	
	/**
	 * Delete existing showing by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> deleteShowing(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<>("Showing id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		
		showingService.delete(id);
		return new ResponseEntity<>("Showing is deleted", HttpStatus.OK);
	}
	
	/**
	 * Get showing by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getShowingById(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<>("Showing id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		
		ShowingDto showingDto = showingService.findById(id);
		return new ResponseEntity<>(showingDto, HttpStatus.OK);
	}
	
	/**
	 * Get all showings using pagination
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAllShowings(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		PagingResultDto pagingResult = showingService.findAll(page, size);
		return new ResponseEntity<>(pagingResult, HttpStatus.OK); 
	}
}
