package com.PRJ321x_namtqFX20225.asm3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public Booking findById(int id);
}
