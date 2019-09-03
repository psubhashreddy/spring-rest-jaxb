package com.boot.rest.jaxb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.rest.jaxb.domain.Person;
import com.boot.rest.jaxb.model.PersonEntity;
import com.boot.rest.jaxb.repository.PersonRepository;


@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public final Person getPersonDetails(int id) {
		final PersonEntity personEntity = personRepository.findOne(id);
		Person person = null;
        if(personEntity != null) {
        	person = new Person();
        	person.setName(personEntity.getName());
        	person.setAge(personEntity.getAge());
        }
        return person;
	}
}
