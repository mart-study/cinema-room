package com.movie.cinemaroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.dto.PagingResultDto;
import com.movie.cinemaroom.exception.CinemaNotFoundException;
import com.movie.cinemaroom.exception.RequirementNotCompleteException;
import com.movie.cinemaroom.model.Cinema;
import com.movie.cinemaroom.model.Showing;
import com.movie.cinemaroom.repository.CinemaRepository;
import com.movie.cinemaroom.repository.ShowingRepository;
import com.movie.cinemaroom.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private ShowingRepository showingRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CinemaDto save(CinemaDto cinemaDto) {
		if (cinemaDto.getShowings() == null) {
			throw new RequirementNotCompleteException("Cinema need to have a minimum one of Showing.");
		}
		
		if (cinemaDto.getShowings().isEmpty()) {
			throw new RequirementNotCompleteException("Cinema need to have a minimum one of Showing.");
		}
		
		cinemaDto.getShowings().forEach(showing -> {
			if (showing.getShowingId() == null) {
				throw new RequirementNotCompleteException("Cinema need to have a minimum one of Showing.");
			}
			
			if (showing.getShowingId().isBlank()) {
				throw new RequirementNotCompleteException("Cinema need to have a minimum one of Showing.");
			}
			
			Optional<Showing> showingOpt = showingRepository.findById(showing.getShowingId());
			if (showingOpt.isEmpty()) {
				throw new RequirementNotCompleteException("Showing with id: " + showing.getShowingId() 
					+ " is not existed.");
			}
		});
		
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
	public PagingResultDto findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		List<CinemaDto> cinemaDtoList = new ArrayList<>();
		Page<Cinema> cinemaPagedResult = cinemaRepository.findAll(paging);
		if (cinemaPagedResult.hasContent()) {
			List<Cinema> cinemaList = cinemaPagedResult.getContent();
			cinemaList.forEach(cinema -> {
				CinemaDto cinemaDto = modelMapper.map(cinema, CinemaDto.class);
				cinemaDtoList.add(cinemaDto);
			});
		}
		
		PagingResultDto pagingResult = new PagingResultDto(cinemaDtoList, 
				cinemaPagedResult.getNumberOfElements(), cinemaPagedResult.getNumber(), 
				cinemaPagedResult.getTotalPages(), cinemaPagedResult.getTotalElements());
		return pagingResult;
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
