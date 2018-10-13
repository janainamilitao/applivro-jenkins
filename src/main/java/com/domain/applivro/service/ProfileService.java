package com.domain.applivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.applivro.exceptions.EmailUniqueException;
import com.domain.applivro.model.Profile;
import com.domain.applivro.repository.ProfileRepository;

@Service
public class ProfileService {
     
    @Autowired
    private ProfileRepository repository;
     
    public ProfileService(ProfileRepository profileRepository) {
    		this.repository = profileRepository;
	}

	public List<Profile> findAll() {
        return repository.findAll();
    }
     
    public Profile findOne(Long id) {
        return repository.findById(id).orElse(null);
    }
     
    public Profile save(Profile post) throws Exception {
    	
    	Optional<Profile> optional = repository.findByEmail(post.getEmail());
    	
    	if(optional.isPresent() && post.getId()==null) {
    		throw new EmailUniqueException();
    	}
    	
        return repository.save(post);
       
    }
     
    public void delete(Profile profile) {
        repository.delete(profile);
    }
    
    public void delete(Long profile) {
        repository.deleteById(profile);
    }
}