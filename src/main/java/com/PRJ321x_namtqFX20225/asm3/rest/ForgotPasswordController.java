package com.PRJ321x_namtqFX20225.asm3.rest;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRJ321x_namtqFX20225.asm3.dao.UserRepository;
import com.PRJ321x_namtqFX20225.asm3.dto.ForgotPasswordDto;
import com.PRJ321x_namtqFX20225.asm3.entity.User;

@RestController
@RequestMapping("/api/forgotPassword")
public class ForgotPasswordController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// logic to send email and reset password 
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody ForgotPasswordDto forgotPasswordDto) {
		if (!userRepository.existsByEmail(forgotPasswordDto.getEmail())) {
			return new ResponseEntity<>("Email does not exist!", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>("Sent email to reset password.", HttpStatus.OK);
	}
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword (@RequestBody ForgotPasswordDto forgotPasswordDto){
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		if (!Pattern.matches(passwordRegex, forgotPasswordDto.getPassword())) {
			return new ResponseEntity<>(
					"Password not strong. Must contain at least 8 characters, including uppercase, lowercase, numbers, and special characters",
					HttpStatus.BAD_REQUEST);
		}
		if (!forgotPasswordDto.getPassword().equals(forgotPasswordDto.getMatchingPassword())) {
			return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
		}
        User user = userRepository.findByEmail(forgotPasswordDto.getEmail());
		user.setPassword(passwordEncoder.encode(forgotPasswordDto.getPassword()));
		user.setMatchingPassword(passwordEncoder.encode(forgotPasswordDto.getMatchingPassword()));
		userRepository.save(user);
		return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
	}
}
