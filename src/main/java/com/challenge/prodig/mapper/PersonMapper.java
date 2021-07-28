package com.challenge.prodig.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.challenge.prodig.dto.PersonDTO;
import com.challenge.prodig.model.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO mapToDTO(Person person);

}
