package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DepartmentDAO {
	@PersistenceContext
	EntityManager em;

	
	@Transactional
	public void create(Department department) {
		em.persist(department);
	}

	public List<Department> findAll() {
		TypedQuery<Department> query = em.createQuery("SELECT D FROM Department D",Department.class);
		return query.getResultList();		
	}

	public Department findByCode(String code) {		
		TypedQuery<Department> query = em.createQuery("SELECT D FROM Department D where D.code=\""+code+"\"",Department.class);
		return query.getSingleResult();
	}

	public Department findById(Long id) {
		return em.find(Department.class, id);
	}

	@Transactional
	public Department update(Department department) {
		return em.merge(department);
	}

	@Transactional
	public void deleteById(Long id) {
		Department department = findById(id);
		delete(department);
	}
	@Transactional
	public void delete(Department department) {
		em.remove(department);
	}

}
