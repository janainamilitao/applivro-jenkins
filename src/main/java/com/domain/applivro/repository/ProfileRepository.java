package com.domain.applivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.applivro.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> { }