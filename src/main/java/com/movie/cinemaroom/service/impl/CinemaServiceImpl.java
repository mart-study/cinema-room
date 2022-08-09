package com.movie.cinemaroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.exception.CinemaNotFoundException;
import com.movie.cinemaroom.model.Cinema;
import com.movie.cinemaroom.repository.CinemaRepository;
import com.movie.cinemaroom.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CinemaDto save(CinemaDto cinemaDto) {
		Cinema cinema = modelMapper.map(cinemaDto, Cinema.class);
		cinema.setActive(true);
		cinemaRepository.save(cinema);
		cinemaDto = modelMapper.map(cinema, CinemaDto.class);
		return cinemaDto;
	}

	@Override
	public CinemaDto update(CinemaDto cinemaDto) {
		Optional<Cinema> cinemaOpt = cinemaRepository.findById(cinemaDto.getScreenId());
		if (cinemaOpt.isEmpty()) {
			throw new CinemaNotFoundException("Cinema with screen id: " + cinemaDto.getScreenId() + " is not found.");
		}
		
		Cinema cinema = cinemaOpt.get();
		cinema.setMaxSeats(cinemaDto.getMaxSeats());
		cinema.setSreenSize(cinemaDto.getSreenSize());
		
		cinema = cinemaRepository.save(cinema);
		cinemaDto = modelMapper.map(cinema, CinemaDto.class);
		return cinemaDto;
	}

	@Override
	public CinemaDto findById(String id) {
		Optional<Cinema> cinemaOpt = cinemaRepository.findById(id);
		if (cinemaOpt.isEmpty()) {
			throw new CinemaNotFoundException("Cinema with screen id: " + id + " is not found.");
		}
		CinemaDto cinemaDto = modelMapper.map(cinemaOpt.get(), CinemaDto.class);
		return cinemaDto;
	}

	@Override
	public List<CinemaDto> findAll() {
		List<CinemaDto> cinemaDtoList = new ArrayList<>();
		Iterable<Cinema> cinemaList = cinemaRepository.findAll();
		if (cinemaList != null) {
			cinemaList.forEach(cinema -> {
				CinemaDto cinemaDto = modelMapper.map(cinema, CinemaDto.class);
				cinemaDtoList.add(cinemaDto);
			});
		}
		
		return cinemaDtoList;
	}

	@Override
	public void delete(String id) {
		Optional<Cinema> cinemaOpt = cinemaRepository.findById(id);
		if (cinemaOpt.isEmpty()) {
			throw new CinemaNotFoundException("Cinema with screen id: " + id + " is not found.");
		}
		
		Cinema cinema = cinemaOpt.get();
		cinema.setActive(false);
		cinemaRepository.save(cinema);
	}
	
	
}
