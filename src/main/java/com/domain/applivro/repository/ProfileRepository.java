package com.domain.applivro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.applivro.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	public Optional<Profile> findByEmail(String email);
	
}