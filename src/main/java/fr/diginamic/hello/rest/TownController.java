package fr.diginamic.hello.rest;

import java.util.List;
import java.util.Objects;

import fr.diginamic.hello.Repository.VilleRepository;
import fr.diginamic.hello.exceptionHandler.FunctionalException;
import fr.diginamic.hello.service.DepartmentService;
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
    @Autowired
    private DepartmentService departmentService;

	private void validateTown(Town town) throws FunctionalException {
		if(town.getNbInhabitants() > 10){
			if(town.getName().length() > 2 ){
				if(town.getDepartment().getCode().length() == 2){
					if(!Objects.equals(townService.getTownByName(town.getName()).getDepartment().getName(), town.getDepartment().getName())){ // le departement no doit pas déjà contenir la ville

					}else{
						throw new FunctionalException("Le département de cette ville contient déjà la ville");
					}
				}else{
					throw new FunctionalException("Le code du département n'est pas valide");
				}
			}else{
				throw new FunctionalException("Le nom de la ville n'est pas valide");
			}
		}else{
			throw new FunctionalException("Le nombre d'habitants de la ville n'est pas valide");
		}
	}


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
	public Iterable<Town> getTownByNameStart(@PathVariable("nameStart") String nameStart) throws FunctionalException{
		if(townService.getTownByNameStart(nameStart) != null){
			return townService.getTownByNameStart(nameStart);
		}else{
			throw new FunctionalException("Aucune ville dont le nom commence par " + nameStart + " n'a été trouvée");
		}
	}


	@GetMapping("/findByNbInhabitantsGreaterThan/{min}")
	public Iterable<Town> getTownByNbInhabitantsGreaterThan(@PathVariable("min") Integer min) throws FunctionalException {
		if(townService.findByNbInhabitantsGreaterThan(min) != null){
			return townService.findByNbInhabitantsGreaterThan(min);
		}else{
			throw new FunctionalException("Aucune ville n'a une population supérieure à " + min);
		}
	}



	@GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
	public Iterable<Town> findByNbInhabitantsBetween(@PathVariable("min") Integer min,@PathVariable("max") Integer max) throws FunctionalException {
		if(townService.findByNbInhabitantsBetween(min, max) != null){
			return townService.findByNbInhabitantsBetween(min,max);
		}else{
			throw new FunctionalException("Aucune ville n'a une population comprise entre " + min + " et " + max);
		}
	}



	@GetMapping("/findByDepartmentCodeAndNbInhabitantsGreaterThan/{codeDep}/{min}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min) throws FunctionalException {
		if(townService.findByDepartmentCodeAndNbInhabitantsGreaterThan(codeDep, min) != null){
			return townService.findByDepartmentCodeAndNbInhabitantsGreaterThan(codeDep,min);
		}else{
			throw new FunctionalException("Aucune ville n'a une population supérieur à " + min + " dans le département " + codeDep);
		}
	}



	@GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min,@PathVariable("max") Integer max) throws FunctionalException {
		if(townService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep,min,max) != null){
			return townService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max);
		}else{
			throw new FunctionalException("Aucune ville n'a une population comprise entre" + min + " et " + max + " dans le département " + codeDep);
		}
	}


	@GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{size}")
	public Iterable<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("size") Integer size) throws FunctionalException {
		if(townService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep, size) != null) {
			return townService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep, size);
		}else if(size == 0 || size < 0){
			throw new FunctionalException("Taille de la recherche incorrecte");
		}else{
			throw new FunctionalException("Aucun département trouver avec " + codeDep);
		}
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