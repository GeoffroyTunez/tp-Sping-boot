package com.aplose.digihello.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aplose.digihello.exception.DigiHelloException;
import com.aplose.digihello.model.Department;
import com.aplose.digihello.model.Town;
import com.aplose.digihello.repository.TownRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.NoResultException;


@Service
public class TownService {
	@Autowired
	TownRepository townRepository;
	@Autowired
	DepartmentService departmentService;
	
	public Iterable<Town> getAllTowns(){
		return townRepository.findAll();
	}
	
	public Town getTown(Long id) throws DigiHelloException {
		Optional<Town> result = townRepository.findById(id);
		if(result.isEmpty()) {
			throw new DigiHelloException("Ville non trouvée pour id = "+id);
		}
		return result.get();
	}
	
	public Town getTownByName(String name) {		
		return townRepository.findByName(name);
	}
	
	public boolean addTown(Town town) throws DigiHelloException {
		validateTown(town);
		Town result = townRepository.findByName(town.getName());
		if (result!=null) {
			return false;
		}else {
			townRepository.save(town);
			return true;
		}		
	}
	public boolean updateTown(Town town) throws DigiHelloException {
		validateTown(town);
			Optional<Town> result = townRepository.findById(town.getId());
			if (result.isEmpty()) {
				return false;
			}
			Town townToUpdate = result.get();
			townToUpdate.setName(town.getName());
			townRepository.save(townToUpdate);
			return true;
	}
	public boolean deleteTown(Long id) {
		Optional<Town> result = townRepository.findById(id);
		if (result.isEmpty()) {
			return false;
		}
		townRepository.deleteById(id);
		return true;
	}

	public Iterable<Town> getTownByNameStart(String nameStart) throws DigiHelloException {
		var result = townRepository.findByNameStartingWith(nameStart);
		if(result==null) {
			throw new DigiHelloException("Aucune ville dont le nom commence par "+nameStart+" n’a été trouvée");
		}
		return result;
	}

	public Iterable<Town> findByNbInhabitantsGreaterThan(Integer min) throws DigiHelloException {
		var result = townRepository.findByNbInhabitantsGreaterThan(min);
		if(result==null) {
			throw new DigiHelloException("Aucune ville n’a une population supérieure à "+min);
		}
		return result;
	}

	public Iterable<Town> findByNbInhabitantsBetween(Integer min, Integer max) throws DigiHelloException {
		var result = townRepository.findByNbInhabitantsBetween(min, max);
		if (result==null) {
			throw new DigiHelloException("Aucune ville n’a une population comprise entre "+min+" et "+max);
		}
		return result;
	}
	
	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer min) throws DigiHelloException {
		var result = townRepository.findByDepartmentCodeAndNbInhabitantsGreaterThan(departmentCode,min);
		if (result==null) {
			throw new DigiHelloException("Aucune ville n’a une population supérieure à "+min+" dans le département "+departmentCode);
		}
		return result;
	}

	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer min, Integer max) throws DigiHelloException {
		var result = townRepository.findByDepartmentCodeAndNbInhabitantsBetween(departmentCode,min, max);
		if (result==null) {
			throw new DigiHelloException("Aucune ville n’a une population comprise entre "+min+" et "+max+" dans le département "+departmentCode);
		}
		return result;
	}
	public Iterable<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Integer size) throws DigiHelloException {
		var result = townRepository.findByDepartmentCodeOrderByNbInhabitantsDesc(departmentCode,Pageable.ofSize(size)).getContent();
		if (result==null) {
			throw new DigiHelloException("Aucune ville n’a une population supérieure à "+size+" dans le département "+departmentCode);
		}
		return result;
	}
	
	private void validateTown(Town t) throws DigiHelloException {
		//on peut aussi utiliser la validation jakarta...
		
		//BR001
		if(t.getNbInhabitants()<10) {
			throw new DigiHelloException("Une ville a, au moins, 10 habitants.");
		}
		//BR002
		if(t.getName().length()<2) {
			throw new DigiHelloException("Le nom d'une ville a, au moins, deux caractères.");
		}
		//BR003
		if (t.getDepartment().getCode().length()!=2) {
			throw new DigiHelloException("Le code département doit être de deux caractères.");
		}
		//BR004
		Town t2 = townRepository.findByNameAndDepartmentCode(t.getName(), t.getDepartment().getCode());
		if(t2!=null) {
			throw new DigiHelloException("Le nom de la ville est unique pour un département donné.");
		}
		//BR005
		//
		//............
		

		
	}

	
}
