package com.PRJ321x_namtqFX20225.asm3.rest;

import java.util.Collections;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRJ321x_namtqFX20225.asm3.dao.RoleRepository;
import com.PRJ321x_namtqFX20225.asm3.dao.UserRepository;
import com.PRJ321x_namtqFX20225.asm3.dto.LoginDto;
import com.PRJ321x_namtqFX20225.asm3.dto.SignUpDto;
import com.PRJ321x_namtqFX20225.asm3.entity.Role;
import com.PRJ321x_namtqFX20225.asm3.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// logic to sign in 
	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
		if (!userRepository.existsByEmail(loginDto.getEmail())) {
			return new ResponseEntity<>("Email does not exist!", HttpStatus.BAD_REQUEST);
		}
		User user = userRepository.findByEmail(loginDto.getEmail());
		if (user.isEnabled()) {
			Authentication authentication;
			try {
				authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			} catch (Exception e) {
				return new ResponseEntity<>("Wrong password.", HttpStatus.UNAUTHORIZED);
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new ResponseEntity<>("Signed-in successfully!.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User had been locked.", HttpStatus.UNAUTHORIZED);
		}
	}

	// sign up for user
	@PostMapping("/signup/user")
	public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String phoneRegex = "^0[0-9]{9}$";
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
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
		Role roles = roleRepository.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(roles));
		userRepository.save(user);
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	// sign up for admin
	@PostMapping("/signup/admin")
	public ResponseEntity<String> registerAdmin(@RequestBody SignUpDto signUpDto) {
		User user = new User();
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setEnabled(true);
		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		userRepository.save(user);
		return new ResponseEntity<>("Admin registered successfully", HttpStatus.OK);
	}

}
