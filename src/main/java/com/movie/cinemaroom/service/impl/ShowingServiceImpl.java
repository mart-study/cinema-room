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

import com.movie.cinemaroom.dto.PagingResultDto;
import com.movie.cinemaroom.dto.ShowingDto;
import com.movie.cinemaroom.exception.ShowingNotFoundException;
import com.movie.cinemaroom.model.Showing;
import com.movie.cinemaroom.repository.ShowingRepository;
import com.movie.cinemaroom.service.ShowingService;

@Service
public class ShowingServiceImpl implements ShowingService {

	@Autowired
	private ShowingRepository showingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ShowingDto save(ShowingDto showingDto) {
		Showing showing = modelMapper.map(showingDto, Showing.class);
		showing.setActive(true);
		showing = showingRepository.save(showing);
		showingDto = modelMapper.map(showing, ShowingDto.class);
		return showingDto;
	}

	@Override
	public ShowingDto update(ShowingDto showingDto) {
		Optional<Showing> showingOpt = showingRepository.findById(showingDto.getShowingId());
		if (showingOpt.isEmpty()) {
			throw new ShowingNotFoundException("Showing id: " + showingDto.getShowingId() + " is not found.");
		}
		
		Showing showing = showingOpt.get();
		showing.setNumSeats(showingDto.getNumSeats());
		showing.setStartTime(showingDto.getStartTime());
		showing = showingRepository.save(showing);
		showingDto = modelMapper.map(showing, ShowingDto.class);
		return showingDto;
	}

	@Override
	public ShowingDto findById(String id) {
		Optional<Showing> showingOpt = showingRepository.findById(id);
		if (showingOpt.isEmpty()) {
			throw new ShowingNotFoundException("Showing id: " + id + " is not found.");
		}
		
		ShowingDto showingDto = modelMapper.map(showingOpt.get(), ShowingDto.class);
		return showingDto;
	}

	@Override
	public PagingResultDto findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Showing> showingPagedResult = showingRepository.findAll(paging);
		List<ShowingDto> showingDtoList = new ArrayList<>();
		
		if (showingPagedResult.hasContent()) {
			List<Showing> showingList = showingPagedResult.getContent();
			showingList.forEach(showing -> {
				ShowingDto showingDto = modelMapper.map(showing, ShowingDto.class);
				showingDtoList.add(showingDto);
			});		
		}
		
		PagingResultDto pagingResult = new PagingResultDto(showingDtoList, showingPagedResult.getNumberOfElements(),
				showingPagedResult.getNumber(), showingPagedResult.getTotalPages(), showingPagedResult.getTotalElements());
		return pagingResult;
	}

	@Override
	public void delete(String id) {
		Optional<Showing> showingOpt = showingRepository.findById(id);
		if (showingOpt.isEmpty()) {
			throw new ShowingNotFoundException("Showing id: " + id + " is not found.");
		}
		
		Showing showing = showingOpt.get();
		showing.setActive(false);
		showingRepository.save(showing);
	}

}
