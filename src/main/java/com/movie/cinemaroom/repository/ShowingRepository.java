package com.movie.cinemaroom.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.movie.cinemaroom.model.Showing;

@Repository
public interface ShowingRepository extends PagingAndSortingRepository<Showing, String> {

}
