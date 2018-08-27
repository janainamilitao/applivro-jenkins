package com.domain.applivro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.domain.applivro.model.Profile;

// ---------------------------------------------------------------
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED) // roda todos os  testes idenpendente de ter alguma falha
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource( "classpath:application-test.properties" )
@Sql( value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
@Sql( value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
// ---------------------------------------------------------------
public class ProfileRepositoryTest {
	
	@Autowired
	private ProfileRepository repository;
	
	@Test
	public void find_profile_by_email() {
	  Optional<Profile> optional =	repository.findByEmail("aecio@gmail.com");
	  
	  assertThat(optional.isPresent()).isTrue();
	  
	  Profile profile = optional.get();
	  assertThat(profile.getName()).isEqualTo("Aécio Pires");
	}
	
}
