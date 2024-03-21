package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.List;

import com.PRJ321x_namtqFX20225.asm3.entity.Booking;

public interface BookingService {

	Booking save (Booking theBooking);

	List<Booking> findByDoctorId(int doctorId);

	List<Booking> findByUserId(int id);

}
