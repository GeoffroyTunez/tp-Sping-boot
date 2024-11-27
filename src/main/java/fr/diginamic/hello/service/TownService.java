package fr.diginamic.hello.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.TownDAO;
import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.NoResultException;


@Service
public class TownService {
	@Autowired
	TownDAO townDAO;
	@Autowired
	DepartmentService departmentService;
	
//	@PostConstruct
	public void init() {
		//first time we create some towns because db is empty...
		townDAO.create(new Town("Paris",2133111,departmentService.findByCode("75")));
		townDAO.create(new Town("Marseille", 873076,departmentService.findByCode("13")));
		townDAO.create(new Town("Lyon", 522250,departmentService.findByCode("69")));
	}
	
	public List<Town> getAllTowns(){
		return townDAO.findAll();
	}
	
	public Town getTown(Long id) {
		Town result = null;
		try {
			result = townDAO.find(id);
		}catch(NoResultException nre) {
		}
		return result;
	}
	
	public Town getTownByName(String name) {
		Town result = null;
		try {
			result = townDAO.findByName(name);
		}catch(NoResultException nre) {
		}
		return result;
	}
	
	public boolean addTown(Town town) {
		try {
			Town result = townDAO.findByName(town.getName());
			return false;
		}catch(NoResultException nre) {
			townDAO.create(town);
			return true;
		}		
	}
	public boolean updateTown(Town town) {
		try {
			Town result = townDAO.find(town.getId());
			result.setName(town.getName());
			result.setNbInhabitants(town.getNbInhabitants());
			townDAO.update(result);
			return true;
		}catch(NoResultException nre) {
			return false;
		}
	}
	public boolean deleteTown(Long id) {
		try {
			Town result = townDAO.find(id);
			townDAO.deleteById(id);
			return true;
		}catch (NoResultException nre) {
			return false;			
		}
	}

	public List<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String codeDep, Integer n) {
		return townDAO.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,n);
	}

	public List<Town> findByDepartmentCodeAndNbInhabitantsBetween(String codeDep, Integer min, Integer max) {
		return townDAO.findByDepartmentCodeAndNbInhabitantsBetween(codeDep,min,max);
	}

}
