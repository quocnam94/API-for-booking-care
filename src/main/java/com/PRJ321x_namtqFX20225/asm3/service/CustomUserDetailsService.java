package com.PRJ321x_namtqFX20225.asm3.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PRJ321x_namtqFX20225.asm3.dao.UserRepository;
import com.PRJ321x_namtqFX20225.asm3.entity.Role;
import com.PRJ321x_namtqFX20225.asm3.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    User user = userRepository.findByEmail(email);
	    Set<GrantedAuthority> authorities = new HashSet<>();

	    for (Role role : user.getRoles()) {
	        authorities.add(new SimpleGrantedAuthority(role.getName()));
	    }

	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(email);
//		Set<GrantedAuthority> authorities = user.getRoles().stream()
//				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
//	}
}
