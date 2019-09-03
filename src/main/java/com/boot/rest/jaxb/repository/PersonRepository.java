package com.boot.rest.jaxb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.rest.jaxb.model.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{

}
