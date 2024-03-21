package com.PRJ321x_namtqFX20225.asm3.rest;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRJ321x_namtqFX20225.asm3.dao.RoleRepository;
import com.PRJ321x_namtqFX20225.asm3.dao.UserRepository;
import com.PRJ321x_namtqFX20225.asm3.dto.SignUpDto;
import com.PRJ321x_namtqFX20225.asm3.entity.Booking;
import com.PRJ321x_namtqFX20225.asm3.entity.Role;
import com.PRJ321x_namtqFX20225.asm3.entity.User;
import com.PRJ321x_namtqFX20225.asm3.service.BookingService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BookingService bookingService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
// logic to sign up doctor
	@PostMapping("/signup")
	public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String phoneRegex = "^0[0-9]{9}$";
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		// valid fields
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (signUpDto.getEmail() == null || !Pattern.matches(emailRegex, signUpDto.getEmail())) {
			return new ResponseEntity<>("Wrong email format", HttpStatus.BAD_REQUEST);
		}
		if (signUpDto.getPhoneNumber() == null || !Pattern.matches(phoneRegex, signUpDto.getPhoneNumber())) {
			return new ResponseEntity<>("Wrong phone format. Must contain 10 digits, start with 0",
					HttpStatus.BAD_REQUEST);
		}
		if (signUpDto.getPassword() == null || signUpDto.getMatchingPassword() == null
				|| !Pattern.matches(passwordRegex, signUpDto.getPassword())) {
			return new ResponseEntity<>(
					"Password not strong. Must contain at least 8 characters, including uppercase, lowercase, numbers, and special characters",
					HttpStatus.BAD_REQUEST);
		}
		if (!signUpDto.getPassword().equals(signUpDto.getMatchingPassword())) {
			return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setFullName(signUpDto.getFullName());
		user.setGender(signUpDto.getGender());
		user.setEmail(signUpDto.getEmail());
		user.setPhoneNumber(signUpDto.getPhoneNumber());
		user.setAddress(signUpDto.getAddress());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setMatchingPassword(passwordEncoder.encode(signUpDto.getMatchingPassword()));
		user.setEnabled(true);
		user.setAward(signUpDto.getAward());
		user.setDescription(signUpDto.getDescription());
		user.setEducation(signUpDto.getEducation());
		user.setSpecialty(signUpDto.getSpecialty());
		Role roles = roleRepository.findByName("ROLE_DOCTOR").get();
		user.setRoles(Collections.singleton(roles));
		userRepository.save(user);
		return new ResponseEntity<>("Doctor registered successfully", HttpStatus.OK);
	}

	// logic to lock and unlock user
	@PatchMapping("/lock/{id}")
	public ResponseEntity<String> lock(@PathVariable("id") int id, @RequestBody User theUser) {
		User existingUser = userRepository.findById(id);
	    if (existingUser == null) {
	        return new ResponseEntity<>("Id '" + id + "' does not exist!", HttpStatus.BAD_REQUEST);
	    }
	    if (theUser.getLockReason() == null || theUser.getLockReason().trim().isEmpty()) {
	        return new ResponseEntity<>("Missing reason to lock", HttpStatus.BAD_REQUEST);
	    }
	    existingUser.setEnabled(false);
	    existingUser.setLockReason(theUser.getLockReason());
	    userRepository.save(existingUser);
	    return new ResponseEntity<>("Locked " + existingUser.getEmail() + " due to " + existingUser.getLockReason(),
	            HttpStatus.OK);
	}

	@PatchMapping("/unlock/{id}")
	public ResponseEntity<String> unlock(@PathVariable("id") int id) {
		User existingUser = userRepository.findById(id);
		if (existingUser == null) {
			return new ResponseEntity<>("User with id '" + id + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		existingUser.setEnabled(true);
		userRepository.save(existingUser);
		return new ResponseEntity<>("Unlock user successfully", HttpStatus.OK);
	}
	
	// get detail user and doctor
	@GetMapping("/detailBookingUser/{userId}")
	public ResponseEntity<String> detailBookingUser(@PathVariable("userId") int userId){
		List<Booking> existingUser = bookingService.findByUserId(userId);
		if (existingUser.isEmpty()) {
			return new ResponseEntity<>("Booking with user id '" + userId + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Result: " + existingUser.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/detailBookingDoctor/{doctorId}")
	public ResponseEntity<String> detailBookingDoctor(@PathVariable("doctorId") int doctorId){
		List<Booking> existingDoctor = bookingService.findByDoctorId(doctorId);
		if (existingDoctor.isEmpty()) {
			return new ResponseEntity<>("Booking with doctor id '" + doctorId + "' does not exist!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Result: " + existingDoctor.toString(), HttpStatus.OK);
	}

}
