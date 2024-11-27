package fr.diginamic.hello.service;

import java.util.Optional;

import fr.diginamic.hello.Repository.VilleRepository;
import fr.diginamic.hello.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.Town;


@Service
public class TownService {
	@Autowired
	VilleRepository villeRepository;
	@Autowired
	DepartmentService departmentService;

	public Iterable<Town> getAllTowns(){
		return villeRepository.findAll();
	}

	public Optional<Town> getTown(Long id) {
		return villeRepository.findById(id);
	}

	public Town getTownByName(String name) {
		return villeRepository.findByName(name);
	}

	public boolean addTown(Town town) {
		Town result = villeRepository.findByName(town.getName());
		if (result!=null) {
			return false;
		}else {
			town.setId(null);
			Department d = departmentService.findById(town.getDepartment().getId()).get();
			if(d != null){
				town.setDepartment(d);
			}else{
				departmentService.create(town.getDepartment());
			}
			villeRepository.save(town);
			return true;
		}
	}

	public boolean updateTown(Town town) {
		Optional<Town> result = villeRepository.findById(town.getId());
		if (result.isEmpty()) {
			return false;
		}
		Town townToUpdate = result.get();
		townToUpdate.setName(town.getName());
		villeRepository.save(townToUpdate);
		return true;
	}
	public boolean deleteTown(Long id) {
		Optional<Town> result = villeRepository.findById(id);
		if (result.isEmpty()) {
			return false;
		}
		villeRepository.deleteById(id);
		return true;
	}

	public Iterable<Town> getTownByNameStart(String nameStart) {
		return villeRepository.findByNameStartingWith(nameStart);
	}

	public Iterable<Town> findByNbInhabitantsGreaterThan(Integer min) {
		return villeRepository.findByNbInhabitantsGreaterThan(min);
	}

	public Iterable<Town> findByNbInhabitantsBetween(Integer min, Integer max) {
		return villeRepository.findByNbInhabitantsBetween(min, max);
	}

	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer min) {
		return villeRepository.findByDepartmentCodeAndNbInhabitantsGreaterThan(departmentCode,min);
	}

	public Iterable<Town> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer min, Integer max) {
		return villeRepository.findByDepartmentCodeAndNbInhabitantsBetween(departmentCode,min, max);
	}
	public Iterable<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Integer size) {
		return villeRepository.findByDepartmentCodeOrderByNbInhabitantsDesc(departmentCode,Pageable.ofSize(size)).getContent();
	}

}
