package fr.diginamic.hello.rest;

import java.util.List;

import fr.diginamic.hello.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import fr.diginamic.hello.model.Town;
import fr.diginamic.hello.service.TownService;

@RestController
@RequestMapping("/town")
public class TownController {
	@Autowired
	private TownService townService;


	@GetMapping
	public Iterable<Town> getTowns(){
		return townService.getAllTowns();
	}
	@GetMapping("/{id}")
	public Town getTown(@PathVariable("id") Long id) {
		return townService.getTown(id).get();
	}
	@GetMapping("/name/{name}")
	public Town getTownByName(@PathVariable("name") String name) {
		return townService.getTownByName(name);
	}
	@GetMapping("/nameStartingWith/{nameStart}")
	public Iterable<Town> getTownByNameStart(@PathVariable("nameStart") String nameStart) {
		return townService.getTownByNameStart(nameStart);
	}
	@GetMapping("/findByNbInhabitantsGreaterThan/{min}")
	public Iterable<Town> getTownByNbInhabitantsGreaterThan(@PathVariable("min") Integer min) {
		return townService.findByNbInhabitantsGreaterThan(min);
	}
	@GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
	public Iterable<Town> findByNbInhabitantsBetween(@PathVariable("min") Integer min,@PathVariable("max") Integer max) {
		return townService.findByNbInhabitantsBetween(min,max);
	}
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsGreaterThan/{codeDep}/{min}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min) {
		return townService.findByDepartmentCodeAndNbInhabitantsGreaterThan(codeDep,min);
	}
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min,@PathVariable("max") Integer max) {
		return townService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max);
	}
	@GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{size}")
	public Iterable<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("size") Integer size) {
		return townService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,size);
	}

	@PostMapping
	public ResponseEntity<String> createTown(@RequestBody Town town) {
		if (townService.addTown(town)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer la ville, elle existe déjà ou il manque le département : "+town.toString(),HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping
	public ResponseEntity<String> updateTown(@RequestBody Town town) {
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