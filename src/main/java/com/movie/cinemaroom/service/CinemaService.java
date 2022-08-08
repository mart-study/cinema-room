package com.movie.cinemaroom.service;

import java.util.List;

import com.movie.cinemaroom.dto.CinemaDto;

public interface CinemaService {

	public CinemaDto save(CinemaDto cinemaDto);
	public CinemaDto update(CinemaDto cinemaDto);
	public CinemaDto findById(String id);
	public List<CinemaDto> findAll();
	public void delete(String id);
	
}
