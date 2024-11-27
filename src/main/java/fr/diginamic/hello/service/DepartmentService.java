package fr.diginamic.hello.service;

import java.util.List;
import java.util.Optional;

import fr.diginamic.hello.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.DepartmentDAO;
import fr.diginamic.hello.model.Department;

import jakarta.annotation.PostConstruct;


@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;

	//	@PostConstruct
	public void init() {
		create(new Department("75", "Paris"));
		create(new Department("13", "Bouches-du-Rhône"));
		create(new Department("69","Rhône"));
	}

	public boolean create(Department department) {
		try {
			departmentRepository.save(department);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department findByCode(String code) {
		return departmentRepository.findByCode(code);
	}

	public Optional<Department> findById(Long id) {
		return departmentRepository.findById(id);
	}

	public boolean update(Department department) {
		if(department.getId()==null||departmentRepository.findById(department.getId()).isEmpty()) {
			return false;
		}else {
			departmentRepository.save(department);
			return true;
		}
	}
	public boolean delete(Long id) {
		if(id==null||departmentRepository.findById(id).isEmpty()) {
			return false;
		}else {
			departmentRepository.deleteById(id);
			return true;
		}
	}

}