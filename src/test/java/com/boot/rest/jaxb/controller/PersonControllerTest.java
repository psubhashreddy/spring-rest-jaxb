package com.boot.rest.jaxb.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.rest.jaxb.domain.Person;
import com.boot.rest.jaxb.exception.NoDataFoundException;
import com.boot.rest.jaxb.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {
	
	@InjectMocks
	private PersonController personController;
	
	@Mock
	private PersonService personService;
	
	private Person person;
	private static final int ID = 3;
	private static final BigInteger AGE = new BigInteger("21");
	private static final String NAME = "SRINIVAS RAO";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		person = new Person();
		person.setName(NAME);
		person.setAge(AGE);
	}

	@Test
	public void getPersonTest() {
		when(personService.getPersonDetails(anyInt())).thenReturn(person);
		
		ResponseEntity<Person> response = personController.getPersonDetails(ID);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().getAge(), equalTo(AGE));
		assertThat(response.getBody().getName(), equalTo(NAME));
		
		verify(personService).getPersonDetails(ID);

	}
	
	@Test(expected = NoDataFoundException.class)
	public void getPersonNoDataFoundTest() {
		
		personController.getPersonDetails(99);
		
		verify(personService).getPersonDetails(ID);

	}	
}
