package com.aplose.digihello.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.aplose.digihello.model.Town;


public interface TownRepository extends CrudRepository<Town, Long> {
	public Town findByName(String name);
	public List<Town> findByNameStartingWith(String name);
	public Town findByNameAndDepartmentCode(String name, String departmentCode);
	public List<Town> findByNbInhabitantsGreaterThan(Integer nb);	
	public List<Town> findByNbInhabitantsBetween(Integer nbMin, Integer nbMax);	
	public List<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer nb);	
	public List<Town> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer nbMin, Integer nbMax);
	public Page<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Pageable pageable);
}
