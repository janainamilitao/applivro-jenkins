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
        return repository.findById(id).orElse(null);
    }
     
    public Profile save(Profile post) {
        return repository.saveAndFlush(post);
    }
     
    public void delete(Profile profile) {
        repository.delete(profile);
    }
    
    public void delete(Long profile) {
        repository.deleteById(profile);
    }
    
    public void teste() {
    	Profile pro = repository.findByEmail("jana@email.com");
    	System.out.println(pro);
    }
}