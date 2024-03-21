package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.Hospital;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class HospitalDaoImpl implements HospitalDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Hospital> searchByAddress(String theSearch) {
		TypedQuery<Hospital> theQuery = null;
		if (theSearch != null && theSearch.trim().length() > 0) {
			theQuery = entityManager.createQuery("from Hospital where lower(address) like :theSearch", Hospital.class);
			theQuery.setParameter("theSearch", "%" + theSearch.toLowerCase() + "%");
		}
		List<Hospital> theHospitals = theQuery.getResultList();
		return theHospitals;
	}

	@Override
	public List<Hospital> searchByName(String theSearch) {
		TypedQuery<Hospital> theQuery = null;
		if (theSearch != null && theSearch.trim().length() > 0) {
			theQuery = entityManager.createQuery("from Hospital where lower(name) like:theSearch", Hospital.class);
			theQuery.setParameter("theSearch", "%" + theSearch.toLowerCase() + "%");
		}
		List<Hospital> theHospitals = theQuery.getResultList();
		return theHospitals;
	}

	@Override
	public List<Hospital> searchByPrice(String theSearch) {
		TypedQuery<Hospital> theQuery = null;
		if (theSearch != null && theSearch.trim().length() > 0) {
			theQuery = entityManager.createQuery("from Hospital where :theSearch between minPrice and maxPrice",
					Hospital.class);
			theQuery.setParameter("theSearch", theSearch);
		}
		List<Hospital> theHospitals = theQuery.getResultList();
		return theHospitals;
	}

	@Override
	public List<Hospital> searchHospitalBySpecialization(String theSearch) {
		TypedQuery<Hospital> theQuery = null;
		if (theSearch != null && theSearch.trim().length() > 0) {
			theQuery = entityManager.createQuery(
					"select h from Hospital h join specializations s where lower(s.name) like :theSearch",
					Hospital.class);
			theQuery.setParameter("theSearch", "%" + theSearch.toLowerCase() + "%");
		}
		List<Hospital> theHospitals = theQuery.getResultList();
		return theHospitals;
	}

	@Override
	public List<Hospital> findAll() {
		TypedQuery<Hospital> theQuery = null;
		theQuery = entityManager.createQuery("from Hospital", Hospital.class);
		List<Hospital> theHospitals = theQuery.getResultList();
		return theHospitals;
	}

}
