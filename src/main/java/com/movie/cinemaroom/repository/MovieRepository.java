package com.movie.cinemaroom.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.movie.cinemaroom.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
	
	public List<Movie> findByTitleLikeIgnoreCase(String title);
}
