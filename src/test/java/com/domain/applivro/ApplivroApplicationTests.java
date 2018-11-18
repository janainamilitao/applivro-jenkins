package com.domain.applivro;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.applivro.model.Profile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplivroApplicationTests {	
	
	private String patternPostalCode = "[0-9]{5}[\\-]?[0-9]{3}";  /** apenas números com ou sem hifen */	
	private String patternEmail = "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";
	private String patternName = "[\\s\\wÀ-ú0-9]+";
	
	private String patternAge = "[0-9]{1,3}";
	
	private String patternIPAddress = "((^|\\.)((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]?\\d))){4}$";
	
	
	@Test	
	public void testAgeValid() {
		String age = "30";
		Profile profile = new Profile();
		
		if(StringUtils.isNumeric(age)) { 
			profile.setAge(Integer.parseInt(age));
			boolean resultAge = age.matches(patternAge);
			assertTrue(resultAge);
		}	
	}
	
	@Test	
	public void testAgeInvalid() {
		String age = "a";
			
		if(!StringUtils.isNumeric(age)) {			
			boolean resultAge = age.matches(patternAge);
			assertFalse(resultAge);
		}	
	}
	
	@Test	
	public void testNameValid() {
		String name = "Janaina Militão";
		Profile profile = new Profile();
		profile.setName(name);
		boolean resultName = name.matches(patternName);
		assertTrue(resultName);
	}
	
	@Test	
	public void testNameInvalid() {
		String name = "Janaina / Militão";
		
		boolean resultName = name.matches(patternName);
		assertFalse(resultName);
	}
	

	@Test	
	public void testIPAddressValid() {
		String ipAddress = "192.168.0.1";
		
		boolean resultIPAddress = ipAddress.matches(patternIPAddress);
		assertTrue(resultIPAddress);
	}
	
	@Test	
	public void testIPAddressInvalid() {
		String ipAddress = "256.256.256.256";
		
		boolean resultIPAddress = ipAddress.matches(patternIPAddress);
		assertFalse(resultIPAddress);
	}
			
	
	
	@Test
	public void testEmailValid() {
		String email = "janaina.militao@gmail.com";
		Profile profile = new Profile();
		profile.setEmail(email);
		
		boolean resultEmail = email.matches(patternEmail);		
		assertTrue(resultEmail);
	}	
	
	@Test
	public void testEmailInvalid() {
		String email = "www.janainamilitao.com.br";
		
		boolean resultEmail = email.matches(patternEmail);		
		assertFalse(resultEmail);
	}	
	
	@Test
	public void testPostalCodeValid() {			
		String postalCode = "58123-000";		
			
		boolean resultPostalCode =  postalCode.matches(patternPostalCode);
		
		assertTrue(resultPostalCode);
	}
	
	@Test
	public void testPostalCodeInvalid() {			
		String postalCode = "aaaaaa";
	
		
		boolean resultPostalCode =  postalCode.matches(patternPostalCode);
		
		assertFalse(resultPostalCode);
	}

}
