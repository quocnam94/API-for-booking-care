package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRJ321x_namtqFX20225.asm3.dao.BookingDao;
import com.PRJ321x_namtqFX20225.asm3.entity.Booking;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingDao bookingDao;

	@Override
	@Transactional
	public Booking save(Booking theBooking) {
		return bookingDao.save(theBooking);
	}

	@Override
	@Transactional
	public List<Booking> findByDoctorId(int doctorId) {
		return bookingDao.findByDoctorId(doctorId);
	}

	@Override
	@Transactional
	public List<Booking> findByUserId(int id) {
		return bookingDao.findByUserId(id);
	}

}
