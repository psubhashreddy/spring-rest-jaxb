package com.boot.rest.jaxb.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.rest.jaxb.domain.Person;
import com.boot.rest.jaxb.exception.NoDataFoundException;
import com.boot.rest.jaxb.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@InjectMocks
	private PersonController personController;
	
	@Mock
	private PersonService personService;
	
	private Person person;
	private static final int SUCCESS_ID = 3;
	private static final int FAILURE_ID = 99;
	private static final BigInteger AGE = new BigInteger("21");
	private static final String NAME = "SRINIVAS RAO";
	
	private URL baseURL;
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		person = new Person();
		person.setName(NAME);
		person.setAge(AGE);
		
		this.baseURL = new URL("http://localhost:8080/api/v1/person/id/");
	}

	@Test
	public void getPersonTest() {
		when(personService.getPersonDetails(anyInt())).thenReturn(person);
		
		ResponseEntity<Person> response = personController.getPersonDetails(SUCCESS_ID);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().getAge(), equalTo(AGE));
		assertThat(response.getBody().getName(), equalTo(NAME));
		
		verify(personService).getPersonDetails(SUCCESS_ID);

	}
	
	@Test(expected = NoDataFoundException.class)
	public void getPersonNoDataFoundTest() {
		
		personController.getPersonDetails(FAILURE_ID);
		
		verify(personService).getPersonDetails(FAILURE_ID);

	}	
	
	@Test
	public void getPersonDetailsTest() throws Exception{
		ResponseEntity<Person> response = testRestTemplate.getForEntity(baseURL.toString()+SUCCESS_ID, Person.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().getAge(), equalTo(AGE));
		assertThat(response.getBody().getName(), equalTo(NAME));
	
	}
	
	@Test
	public void getPersonDetailsNotFoundTest() throws Exception{
		ResponseEntity<Person> response = testRestTemplate.getForEntity(baseURL.toString()+FAILURE_ID, Person.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
	
	}
}
