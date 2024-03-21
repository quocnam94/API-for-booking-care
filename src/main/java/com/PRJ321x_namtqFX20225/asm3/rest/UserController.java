package com.PRJ321x_namtqFX20225.asm3.rest;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRJ321x_namtqFX20225.asm3.dao.UserRepository;
import com.PRJ321x_namtqFX20225.asm3.entity.Booking;
import com.PRJ321x_namtqFX20225.asm3.entity.Hospital;
import com.PRJ321x_namtqFX20225.asm3.entity.Result;
import com.PRJ321x_namtqFX20225.asm3.entity.Specialization;
import com.PRJ321x_namtqFX20225.asm3.entity.User;
import com.PRJ321x_namtqFX20225.asm3.service.BookingService;
import com.PRJ321x_namtqFX20225.asm3.service.HospitalService;
import com.PRJ321x_namtqFX20225.asm3.service.ResultService;
import com.PRJ321x_namtqFX20225.asm3.service.SpecializationService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private ResultService resultService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private SpecializationService specializationService;
	
	// get infor user
	@GetMapping("/getProfile/{id}")
	public ResponseEntity<String> getProfile(@PathVariable("id") int id) {
		User theUser = userRepository.findById(id);
		List<Result> theResult = resultService.findByUserId(id);
		if (theUser == null) {
			return new ResponseEntity<>("Id does not exist!", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok("Profile information: " + theUser.toString()+ ".History: " + theResult.toString());
		}
	}

	// update infor user
	@PutMapping("/updateProfile/{id}")
	public ResponseEntity<String> updateProfile(@PathVariable("id") int id, @RequestBody User theUser) {
		String phoneRegex = "^0[0-9]{9}$";
		User existingUser = userRepository.findById(id);
		if (existingUser == null) {
			return new ResponseEntity<>("User with id '" + id + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		if (theUser.getFullName() != null) {
			existingUser.setFullName(theUser.getFullName());
		}
		if (theUser.getAddress() != null) {
			existingUser.setAddress(theUser.getAddress());
		}
		if (theUser.getGender() != null) {
			existingUser.setGender(theUser.getGender());
		}
		if (theUser.getPhoneNumber() != null && !theUser.getPhoneNumber().isEmpty()) {
		    if (!Pattern.matches(phoneRegex, theUser.getPhoneNumber())) {
		        return new ResponseEntity<>("Wrong phone format. Must contain 10 digits, start with 0",
		                HttpStatus.BAD_REQUEST);
		    }
		    existingUser.setPhoneNumber(theUser.getPhoneNumber());
		}
		userRepository.save(existingUser);
		return ResponseEntity.ok("Profile information updated: " + existingUser.toString());
	}
	
	// get infor hospitals
	@GetMapping("/getHospitals")
	public ResponseEntity<String> getHospitals() {
		List<Hospital> theHospitals = hospitalService.findAll();
		return ResponseEntity.ok("Result: " + theHospitals.toString());
	}

	// search 
	@GetMapping("/search/address/{theSearch}")
	public ResponseEntity<String> searchByAddress(@PathVariable String theSearch) {
		List<Hospital> existingHospital = hospitalService.searchByAddress(theSearch);
		if (existingHospital.isEmpty()) {
			return new ResponseEntity<>("No result", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok("Search results: " + existingHospital.toString());
	}

	@GetMapping("/search/name/{theSearch}")
	public ResponseEntity<String> searchByName(@PathVariable String theSearch) {
		List<Hospital> existingHospital = hospitalService.searchByName(theSearch);
		if (existingHospital.isEmpty()) {
			return new ResponseEntity<>("No result", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok("Search results: " + existingHospital.toString());
	}

	@GetMapping("/search/price/{theSearch}")
	public ResponseEntity<String> searchByPrice(@PathVariable String theSearch) {
		List<Hospital> existingHospital = hospitalService.searchByPrice(theSearch);
		if (existingHospital.isEmpty()) {
			return new ResponseEntity<>("No result", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok("Search results: " + existingHospital.toString());
	}

	@GetMapping("/search/searchHospitalBySpecialization/{theSearch}")
	public ResponseEntity<String> searchHospitalBySpecialization(@PathVariable String theSearch) {
		List<Hospital> existingHospital = hospitalService.searchHospitalBySpecialization(theSearch);
		if (existingHospital.isEmpty()) {
			return new ResponseEntity<>("No result", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok("Search results: " + existingHospital.toString());
	}
	
	@GetMapping("/search/specialization/{theSearch}")
	public ResponseEntity<String> searchBySpecialization(@PathVariable String theSearch) {
		List<Specialization> existingSpecialization = specializationService.searchBySpecialization(theSearch);
		if (existingSpecialization.isEmpty()) {
			return new ResponseEntity<>("No result", HttpStatus.BAD_REQUEST);
		}
		for (Specialization specialization : existingSpecialization) {
	        specialization.setCountSearch(specialization.getCountSearch() + 1);
	        specializationService.save(specialization);
	    }
		return ResponseEntity.ok("Search results: " + existingSpecialization.toString());
	}

	@GetMapping("/topSpecialization")
	public ResponseEntity<String> topSpecialization() {
		List<Specialization> topSpecialization = specializationService.topSpecialization();
		return ResponseEntity.ok("Top Specialization: " + topSpecialization.toString());
	}

	@PostMapping("/addBooking/{userId}")
	public ResponseEntity<String> addBooking(@PathVariable int userId, @RequestBody Booking theBooking) {
		String dateRegex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$";
        String timeRegex = "^([01]?\\d|2[0-3]):([0-5]\\d)$";
		String phoneRegex = "^0[0-9]{9}$";
		User existingUser = userRepository.findById(userId);
		if (existingUser == null) {
			return new ResponseEntity<>("User with id '" + userId + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		if (!Pattern.matches(dateRegex, theBooking.getDate()) || !Pattern.matches(dateRegex, theBooking.getDob())) {
			return new ResponseEntity<>("Wrong date format. Must follow format mm/dd/yyyy", HttpStatus.BAD_REQUEST);
		}
		if (!Pattern.matches(timeRegex, theBooking.getTime())) {
			return new ResponseEntity<>("Wrong time format. Must follow format hh:mm", HttpStatus.BAD_REQUEST);
		}
		if (theBooking.getPrice()<=0 ) {
			throw new ErrorException("Invalid amount");
		}
		if (theBooking.getPhoneNumber() == null || !Pattern.matches(phoneRegex, theBooking.getPhoneNumber())) {
			return new ResponseEntity<>("Wrong phone format. Must contain 10 digits, start with 0",
					HttpStatus.BAD_REQUEST);
		}
		Result result = new Result();
	    result.setName(theBooking.getName());
	    result.setAddress(theBooking.getAddress());
	    result.setDoctorId(theBooking.getDoctorId());
	    result.setUserId(userId);
	    result.setGender(theBooking.getGender());
	    resultService.save(result);
		theBooking.setId(0);
		theBooking.setIsAccept(true);
		theBooking.setUserId(userId);
		Booking dbBooking = bookingService.save(theBooking);
		return ResponseEntity.ok("Booking successfully: " + dbBooking);
	}

}
