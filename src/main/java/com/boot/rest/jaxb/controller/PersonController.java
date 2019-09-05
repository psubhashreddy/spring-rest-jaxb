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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@Api(value = "Persons", description = "API to get persons by ID")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "person/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	@ApiOperation(value = "View Person Details in XML output", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ResponseEntity<Person> getPersonDetails(@PathVariable int id) {
		Person person = personService.getPersonDetails(id);
		if(person == null) {
			throw new NoDataFoundException("No Person found with id = "+id)  ;
		}
		return ResponseEntity.ok(person);
	}
	
}
