package com.PRJ321x_namtqFX20225.asm3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRJ321x_namtqFX20225.asm3.dao.BookingRepository;
import com.PRJ321x_namtqFX20225.asm3.entity.Booking;
import com.PRJ321x_namtqFX20225.asm3.entity.Result;
import com.PRJ321x_namtqFX20225.asm3.service.ResultService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ResultService resultService;
	
// show patients
	@GetMapping("/listPatient/{doctorId}")
	public ResponseEntity<String> listPatient(@PathVariable("doctorId") int id) {
		List<Result> existingDoctor = resultService.findByDoctorId(id);
		if (existingDoctor.isEmpty()) {
			return new ResponseEntity<>("Doctor with id " + id + " does not have any bookings", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("List Patients: " + existingDoctor.toString(), HttpStatus.OK);
	}

	// accept or reject booking
	@PatchMapping("/isAccept/{id}")
	public ResponseEntity<String> isAccept(@PathVariable("id") int bookingId, @RequestBody Booking theBooking) {
		Booking existingUser = bookingRepository.findById(bookingId);
		if (existingUser == null) {
			return new ResponseEntity<>("Booking with id '" + bookingId + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		if (theBooking.getRejectReason() == null || theBooking.getRejectReason().trim().isEmpty()) {
			return new ResponseEntity<>("Missing reason to reject", HttpStatus.BAD_REQUEST);
		}
		existingUser.setIsAccept(false);
		existingUser.setRejectReason(theBooking.getRejectReason());
		bookingRepository.save(existingUser);
		return new ResponseEntity<>(
				"Reject user " + existingUser.getUserId() + " due to " + existingUser.getRejectReason(), HttpStatus.OK);
	}
}
