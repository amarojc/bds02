package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ObjectNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> cities = cityRepository.findAll(Sort.by("name"));
		return cities.stream()
				.map(city -> new CityDTO(city))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		city.setName(cityDTO.getName());
		return new CityDTO(cityRepository.save(city));
	}
	
	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectNotFoundException("Cidade não encontrada/City not found " + id);
		}
	}
}
