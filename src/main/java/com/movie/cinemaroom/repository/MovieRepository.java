package com.movie.cinemaroom.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.movie.cinemaroom.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
	
	/**
	 * Find movie by title (get first result)
	 * @param title
	 * @return
	 */
	Optional<Movie> findFirstByTitle(String title);
}
