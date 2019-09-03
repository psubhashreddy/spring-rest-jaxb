package com.boot.rest.jaxb.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.rest.jaxb.domain.Person;
import com.boot.rest.jaxb.exception.NoDataFoundException;
import com.boot.rest.jaxb.model.PersonEntity;
import com.boot.rest.jaxb.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	private PersonEntity personEntity;
	private static final int ID = 3;
	private static final BigInteger AGE = new BigInteger("21");
	private static final String NAME = "SRINIVAS RAO";
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		personEntity = new PersonEntity();
		personEntity.setName(NAME);
		personEntity.setAge(AGE);
	}

	@Test
	public void getPersonTest() {
		when(personRepository.findOne(anyInt())).thenReturn(personEntity);
		
		Person person = personService.getPersonDetails(ID);
		
		assertThat(personEntity.getName(), equalTo(person.getName()));
		assertThat(personEntity.getAge(), equalTo(person.getAge()));
	}
	
	@Test
	public void getPersonNotFoundTest() {
		when(personRepository.findOne(anyInt())).thenReturn(null);
		
		personService.getPersonDetails(99);
		
		assertThat(NoDataFoundException.class, equalTo(NoDataFoundException.class));
		
	}
}
