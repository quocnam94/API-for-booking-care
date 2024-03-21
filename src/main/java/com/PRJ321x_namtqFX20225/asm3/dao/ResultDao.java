package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Result;

public interface ResultDao {
	
	Result save (Result theResult);
	List<Result> findByDoctorId(int id);
	List<Result> findByUserId(int id);
}
