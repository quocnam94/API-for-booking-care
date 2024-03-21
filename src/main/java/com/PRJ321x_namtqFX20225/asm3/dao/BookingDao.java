package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Booking;

public interface BookingDao {
	
	Booking save (Booking theBooking);
	List<Booking> findByDoctorId(int id);
	List<Booking> findByUserId(int id);

}
