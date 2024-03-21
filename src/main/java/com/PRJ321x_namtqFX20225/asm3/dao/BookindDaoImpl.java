package com.PRJ321x_namtqFX20225.asm3.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.Booking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class BookindDaoImpl implements BookingDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Booking save(Booking theBooking) {
		Booking dbBooking = entityManager.merge(theBooking);
		return dbBooking;
	}

	@Override
	public List<Booking> findByDoctorId(int id) {
		TypedQuery<Booking> theQuery = entityManager.createQuery("from Booking where doctorId = :id", Booking.class);
	    theQuery.setParameter("id", id);
	    List<Booking> theBookings = theQuery.getResultList();
	    return theBookings;
	}

	@Override
	public List<Booking> findByUserId(int id) {
		TypedQuery<Booking> theQuery = entityManager.createQuery("from Booking where userId = :id", Booking.class);
	    theQuery.setParameter("id", id);
	    List<Booking> theBookings = theQuery.getResultList();
	    return theBookings;
	}

}
