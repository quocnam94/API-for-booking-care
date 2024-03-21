package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.Specialization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class SpecializationDaoImpl implements SpecializationDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Specialization> topSpecialization() {
		TypedQuery<Specialization> theQuery = entityManager.createQuery(
				"select s from Specialization s " + "order by countSearch desc limit 1",  
		        Specialization.class);
		    List<Specialization> theSpecializations = theQuery.getResultList();
		    return theSpecializations;}

	@Override
	public List<Specialization> searchBySpecialization(String theSearch) {
		TypedQuery<Specialization> theQuery = null;
		if (theSearch != null && theSearch.trim().length() > 0) {
			theQuery = entityManager.createQuery("select s from Specialization s " +
                    "where lower(s.name) like :theSearch", Specialization.class);
			theQuery.setParameter("theSearch", "%" + theSearch.toLowerCase() + "%");
		}
		List<Specialization> theSpecializations = theQuery.getResultList();
		return theSpecializations;
	}

	@Override
	public Specialization save(Specialization theSpecialization) {
		Specialization dbSpecialization = entityManager.merge(theSpecialization);
		return dbSpecialization;
	}

}
