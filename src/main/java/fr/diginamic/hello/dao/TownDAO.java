package fr.diginamic.hello.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.Town;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class TownDAO {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void create(Town town) {
		em.persist(town);
	}
	
	public List<Town> findAll(){
		TypedQuery<Town> query = em.createQuery("SELECT T FROM Town T",Town.class);
		return query.getResultList();
	}
	
	public Town find(Long id) {
		return em.find(Town.class, id);
	}
	
	public Town findByName(String name){
		TypedQuery<Town> query = em.createQuery("SELECT T FROM Town T where T.name=\""+name+"\"",Town.class);
		return query.getSingleResult();
	}

	@Transactional
	public Town update(Town town) {
		return em.merge(town);
	}
	
	@Transactional
	public void delete(Town town) {
		em.remove(town);
	}
	@Transactional
	public void deleteById(Long id) {
		Town t = em.find(Town.class, id);
		em.remove(t);
	}

	public List<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String codeDep, Integer n) {
		TypedQuery<Town> query = em.createQuery("SELECT T FROM Town T where T.department.code=\""+codeDep+"\" order by nbInhabitants desc LIMIT "+n,Town.class);
		return query.getResultList();
	}

	public List<Town> findByDepartmentCodeAndNbInhabitantsBetween(String codeDep, Integer min, Integer max) {
		TypedQuery<Town> query = em.createQuery("SELECT T FROM Town T where T.department.code=\""+codeDep+"\" and T.nbInhabitants > "+min+" and T.nbInhabitants < "+max,Town.class);
		return query.getResultList();
	}
	

}

