package com.domain.applivro.service;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.applivro.exceptions.EmailUniqueException;
import com.domain.applivro.model.Profile;
import com.domain.applivro.repository.ProfileRepository;


@RunWith(SpringRunner.class) // rodar os testes com spring
public class ProfileServiceTest {
	
	public static final String NAME = "Janaina";
	public static final String EMAIL = "janaina@gmail.com";
	public static final String IP = "192.168.25.01";
	public static final String POSTAL_CODE = "58000-300";
	public static final Date DATE_OF_BIRTH = new Date();
	
	@MockBean
	private ProfileRepository profileRepository;
 
	private ProfileService service;
	
	private Profile profile;
	
	@Before
	public void setUp() throws Exception{
		service = new ProfileService(profileRepository);
		
		profile = new Profile();
		profile.setName(NAME);
		profile.setEmail(EMAIL);
		profile.setIpAddress(IP);
		profile.setPostalCode(POSTAL_CODE);
		profile.setDateOfBirth(DATE_OF_BIRTH);
		
		when(profileRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
	}
	
	/*@Test
	public void test_salve_profile_valid() throws Exception{
		service.save(profile);
		
		verify(profileRepository).save(profile);
	}*/
	
	
	@Test(expected = EmailUniqueException.class)
	public void test_save_profile_same_email()  throws Exception{
		when(profileRepository.findByEmail(EMAIL)).thenReturn(Optional.of(profile));
		
		service.save(profile);
	}
}
