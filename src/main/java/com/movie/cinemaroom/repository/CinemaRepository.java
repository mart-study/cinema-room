package com.movie.cinemaroom.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.movie.cinemaroom.model.Cinema;

@Repository
public interface CinemaRepository extends PagingAndSortingRepository<Cinema, String> {

}
