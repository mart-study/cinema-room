package com.movie.cinemaroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	/**
	 * Save movie
	 * @param movieDto
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> saveMovie(@RequestBody MovieDto movieDto) {
		movieService.save(movieDto);
		return new ResponseEntity<>("Movie is created", HttpStatus.CREATED);
	}
	
	/**
	 * Update movie
	 * @param movieDto
	 * @return
	 */
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateMovie(@RequestBody MovieDto movieDto) {
		movieService.update(movieDto);
		return new ResponseEntity<>("Movie is updated", HttpStatus.OK);
	}
	
	/**
	 * Delete movie (change status active to false)
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> deleteMovie(@PathVariable String id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<>("Movie is deleted", HttpStatus.OK);
	}
	
	/**
	 * Get movie detail by id
	 * @param id (required)
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getMovieById(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<>("Movie id is required", HttpStatus.NOT_ACCEPTABLE);
		}
		MovieDto movieDto = movieService.findById(id);
		return new ResponseEntity<>(movieDto, HttpStatus.OK);
	}
	
	/**
	 * Get active movies / find all movie with active status
	 * @return
	 */
	@GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAllActiveMovies() {
		List<MovieDto> movieList = movieService.findAllActiveMovies();
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}
	
	/**
	 * Search movies by title
	 * @param title (required)
	 * @return
	 */
	@GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
		if (title == null || title.isEmpty()) {
			return new ResponseEntity<>("Movie title is required", HttpStatus.NOT_ACCEPTABLE);
		}
		List<MovieDto> movieList = movieService.findByTitle(title);
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}
}
