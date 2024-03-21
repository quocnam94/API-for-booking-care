package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Specialization;

public interface SpecializationService {
	
	public List<Specialization> topSpecialization();

	public List<Specialization> searchBySpecialization(String theSearch);
	
	public Specialization save (Specialization theSpecialization);


}
