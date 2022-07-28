package com.movie.cinemaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> saveMovie(@RequestBody MovieDto movieDto) {
		movieService.save(movieDto);
		return new ResponseEntity<>("Movie is created", HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> updateMovie(@RequestBody MovieDto movieDto) {
		movieService.update(movieDto);
		return new ResponseEntity<>("Movie is updated", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<String> deleteMovie(@PathVariable String id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<>("Movie is deleted", HttpStatus.OK);
	}
}
