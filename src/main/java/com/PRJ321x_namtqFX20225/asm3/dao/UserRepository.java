package com.PRJ321x_namtqFX20225.asm3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PRJ321x_namtqFX20225.asm3.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	User findById(int id);
    Boolean existsByEmail(String email);
    
}
