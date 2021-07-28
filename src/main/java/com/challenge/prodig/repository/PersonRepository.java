package com.challenge.prodig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.prodig.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
