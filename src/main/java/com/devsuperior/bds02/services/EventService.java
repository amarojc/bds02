package com.devsuperior.bds02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;

	
	@Transactional
	public EventDTO update(Long id, EventDTO eventDTO) {
		Event event = eventRepository.getOne(id);
		copyEventDtoToEntityEvent(eventDTO, event);
		event = eventRepository.save(event);

		return new EventDTO(event);
	}
		
	private void copyEventDtoToEntityEvent(EventDTO eventDTO, Event event) {
		event.setName(eventDTO.getName());
		event.setDate(eventDTO.getDate());
		event.setUrl(eventDTO.getUrl());
		
		City city = cityRepository.getOne(eventDTO.getCityId());
		event.setCity(city);
	}
}
