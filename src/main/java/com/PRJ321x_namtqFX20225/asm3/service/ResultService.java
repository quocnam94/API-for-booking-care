package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Result;

public interface ResultService {

	Result save (Result theResult);

	List<Result> findByDoctorId(int doctorId);

	List<Result> findByUserId(int id);

}
