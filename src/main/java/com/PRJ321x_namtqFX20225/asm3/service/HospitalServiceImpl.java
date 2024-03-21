package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRJ321x_namtqFX20225.asm3.dao.HospitalDao;
import com.PRJ321x_namtqFX20225.asm3.entity.Hospital;

import jakarta.transaction.Transactional;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hospitalDao;
	
	@Override
	@Transactional
	public List<Hospital> searchByName(String theSearch) {
		return hospitalDao.searchByName(theSearch);
	}

	@Override
	@Transactional
	public List<Hospital> searchByAddress(String theSearch) {
		return hospitalDao.searchByAddress(theSearch);
	}

	@Override
	@Transactional
	public List<Hospital> searchHospitalBySpecialization(String theSearch) {
		return hospitalDao.searchHospitalBySpecialization(theSearch);
	}

	@Override
	@Transactional
	public List<Hospital> searchByPrice(String theSearch) {
		return hospitalDao.searchByPrice(theSearch);
	}

	@Override
	@Transactional
	public List<Hospital> findAll() {
		return hospitalDao.findAll();
	}

}
