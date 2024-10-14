package com.aplose.digihello.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.digihello.dto.TownDto;
import com.aplose.digihello.exception.DigiHelloException;
import com.aplose.digihello.model.Town;
import com.aplose.digihello.service.TownService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/town")
public class TownController {
	@Autowired
	private TownService townService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<TownDto> getAllTowns(){
		return StreamSupport.stream(townService.getAllTowns().spliterator(),true).map(town -> modelMapper.map(town,TownDto.class)).toList();
	}
	@GetMapping("/{id}")
	public Town getTown(@PathVariable("id") Long id) throws DigiHelloException {
		return townService.getTown(id);
	}
	@GetMapping("/name/{name}")
	public Town getTownByName(@PathVariable("name") String name) {
		return townService.getTownByName(name);
	}
	@GetMapping("/nameStartingWith/{nameStart}")
	public Iterable<Town> getTownByNameStart(@PathVariable("nameStart") String nameStart) throws DigiHelloException {
		return townService.getTownByNameStart(nameStart);
	}
	@GetMapping("/findByNbInhabitantsGreaterThan/{min}")
	public Iterable<Town> getTownByNbInhabitantsGreaterThan(@PathVariable("min") Integer min) throws DigiHelloException {
		return townService.findByNbInhabitantsGreaterThan(min);
	}
	@GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
	public Iterable<Town> findByNbInhabitantsBetween(@PathVariable("min") Integer min,@PathVariable("max") Integer max) throws DigiHelloException {
		return townService.findByNbInhabitantsBetween(min,max);
	}
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsGreaterThan/{codeDep}/{min}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min) throws DigiHelloException {
		return townService.findByDepartmentCodeAndNbInhabitantsGreaterThan(codeDep,min);
	}
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min,@PathVariable("max") Integer max) throws DigiHelloException {
		return townService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max);
	}
	@GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{size}")
	public Iterable<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("size") Integer size) throws DigiHelloException {
		return townService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,size);
	}
	
	@PostMapping
	public ResponseEntity<String> createTown(@RequestBody Town town) throws DigiHelloException {
		if (townService.addTown(town)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer la ville, elle existe déjà ou il manque le département : "+town.toString(),HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping
	public ResponseEntity<String> updateTown(@RequestBody Town town) throws DigiHelloException {
		if (townService.updateTown(town)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTown(@PathVariable Long id) {
		if (townService.deleteTown(id)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
		}
	}
	
}
