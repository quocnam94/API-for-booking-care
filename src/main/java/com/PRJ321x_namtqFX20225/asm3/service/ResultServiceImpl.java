package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRJ321x_namtqFX20225.asm3.dao.ResultDao;
import com.PRJ321x_namtqFX20225.asm3.entity.Result;

import jakarta.transaction.Transactional;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultDao resultDao;

	@Override
	@Transactional
	public Result save(Result theResult) {
		return resultDao.save(theResult);
	}

	@Override
	@Transactional
	public List<Result> findByDoctorId(int doctorId) {
		return resultDao.findByDoctorId(doctorId);
	}

	@Override
	@Transactional
	public List<Result> findByUserId(int id) {
		return resultDao.findByUserId(id);
	}

}
