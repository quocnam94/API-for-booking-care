package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Hospital;

public interface HospitalService {
	
	public List<Hospital> searchByName(String theSearch);

	public List<Hospital> searchByAddress(String theSearch);

	public List<Hospital> searchHospitalBySpecialization(String theSearch);

	public List<Hospital> searchByPrice(String theSearch);

	public List<Hospital> findAll();

}
