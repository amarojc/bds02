package com.devsuperior.bds02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@Controller
@RequestMapping(value="/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping()
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> cities = cityService.findAll();
		return ResponseEntity.ok().body(cities);		
	}
}
