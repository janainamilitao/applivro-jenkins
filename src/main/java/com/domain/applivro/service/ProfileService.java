package com.domain.applivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.applivro.model.Profile;
import com.domain.applivro.repository.ProfileRepository;

@Service
public class ProfileService {
     
    @Autowired
    private ProfileRepository repository;
     
    public List<Profile> findAll() {
        return repository.findAll();
    }
     
    public Profile findOne(Long id) {
        return repository.findById(id).get();
    }
     
    public Profile save(Profile post) {
        return repository.saveAndFlush(post);
    }
     
    public void delete(Profile profile) {
        repository.delete(profile);
    }
}