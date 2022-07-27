package com.movie.cinemaroom.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.movie.cinemaroom.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {

}
