package com.movie.cinemaroom.controller;

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

import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.dto.PagingResultDto;
import com.movie.cinemaroom.service.CinemaService;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	/**
	 * Save new cinema
	 * @param cinemaDto
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> saveCinema(@RequestBody CinemaDto cinemaDto) {
		cinemaService.save(cinemaDto);
		return new ResponseEntity<>("Cinema is created", HttpStatus.CREATED);
	}
	
	/**
	 * Update existing cinema
	 * @param cinemaDto
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateCinema(@RequestBody CinemaDto cinemaDto) {
		if (cinemaDto.getScreenId() == null || cinemaDto.getScreenId().isEmpty()) {
			return new ResponseEntity<>("Cinema screen id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		cinemaService.update(cinemaDto);
		return new ResponseEntity<>("Cinema is updated", HttpStatus.OK);
	}
	
	/**
	 * Find cinema by id
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getCinemaById(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<>("Cinema screen id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		CinemaDto cinemaDto = cinemaService.findById(id);
		return new ResponseEntity<>(cinemaDto, HttpStatus.OK);
	}
	
	/**
	 * Get all cinemas with pagination
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAllCinemas(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		PagingResultDto pagingResult = cinemaService.findAll(page, size);
		return new ResponseEntity<>(pagingResult, HttpStatus.OK);
	}
	
	/**
	 * Delete cinema by id
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> deleteCinema(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<>("Cinema id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		cinemaService.delete(id);
		return new ResponseEntity<>("Cinema is deleted", HttpStatus.OK);
	}
}
