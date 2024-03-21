package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRJ321x_namtqFX20225.asm3.dao.SpecializationDao;
import com.PRJ321x_namtqFX20225.asm3.entity.Specialization;

import jakarta.transaction.Transactional;

@Service
public class SpecializationServiceImpl implements SpecializationService {

	@Autowired
	private SpecializationDao specializationDao;
	
	@Override
	@Transactional
	public List<Specialization> topSpecialization() {
		return specializationDao.topSpecialization();
	}

	@Override
	@Transactional
	public List<Specialization> searchBySpecialization(String theSearch) {
		return specializationDao.searchBySpecialization(theSearch);
	}

	@Override
	@Transactional
	public Specialization save(Specialization theSpecialization) {
		return specializationDao.save(theSpecialization);
	}

}
