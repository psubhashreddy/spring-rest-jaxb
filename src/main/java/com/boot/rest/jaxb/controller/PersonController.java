package com.boot.rest.jaxb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.rest.jaxb.domain.Person;
import com.boot.rest.jaxb.exception.NoDataFoundException;
import com.boot.rest.jaxb.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "person/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Person> getPersonDetails(@PathVariable int id) {
		Person person = personService.getPersonDetails(id);
		if(person == null) {
			throw new NoDataFoundException("No Person found with id = "+id)  ;
		}
		return ResponseEntity.ok(person);
	}
	
}
