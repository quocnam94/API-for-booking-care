package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ResultDaoImpl implements ResultDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Result save(Result theResult) {
		Result dbResult = entityManager.merge(theResult);
		return dbResult;
	}

	@Override
	public List<Result> findByDoctorId(int id) {
		TypedQuery<Result> theQuery = entityManager.createQuery("from Result where doctorId = :id", Result.class);
	    theQuery.setParameter("id", id);
	    List<Result> theResults = theQuery.getResultList();
	    return theResults;
	}

	@Override
	public List<Result> findByUserId(int id) {
		TypedQuery<Result> theQuery = entityManager.createQuery("from Result where userId =:id", Result.class);
		theQuery.setParameter("id", id);
		List<Result> theResults = theQuery.getResultList();
		return theResults;
	}

}
