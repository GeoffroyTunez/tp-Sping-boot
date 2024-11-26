package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplose.digihello.dao.DepartmentDAO;
import com.aplose.digihello.model.Department;

import jakarta.annotation.PostConstruct;


@Service
public class DepartmentService {
	@Autowired
	DepartmentDAO departmentDAO;
	
	@PostConstruct
	public void init() {	
		create(new Department("75", "Paris"));
		create(new Department("13", "Bouches-du-Rhône"));
		create(new Department("69","Rhône"));
	}

	public boolean create(Department department) {
		try {
			departmentDAO.create(department);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public List<Department> findAll() {
		 return departmentDAO.findAll();
	}

	public Department findByCode(String code) {
		return departmentDAO.findByCode(code);
	}

	public Department findById(Long id) {
		return departmentDAO.findById(id);
	}
	
	public boolean update(Department department) {
		if(department.getId()==null||departmentDAO.findById(department.getId())==null) {
			return false;
		}else {
			departmentDAO.update(department);
			return true;
		}
	}
	public boolean delete(Long id) {
		if(id==null||departmentDAO.findById(id)==null) {
			return false;
		}else {
			departmentDAO.deleteById(id);
			return true;
		}
	}
	
}
