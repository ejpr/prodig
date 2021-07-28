package com.challenge.prodig.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.challenge.prodig.model.DocumentType;
import com.challenge.prodig.model.Person;

public class PersonTestUtil {

    public static List<Person> makeListPerson() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        // @formatter:off
        Person personA = Person.builder().id(1L)
                               .documentType(DocumentType.CUIT)
                               .documentNumber("123456")
                               .birthDate(sdf.parse("1994-20-01"))
                               .email("youremail@email.com")
                               .build();

        Person personB = Person.builder().id(2L)
                               .documentType(DocumentType.DNI)
                               .documentNumber("0000")
                               .birthDate( sdf.parse("1995-20-5"))
                               .email("youremail2@email.com")
                               .build();

        return  Arrays.asList(personA,personB);

        // @formatter:on
    }
}
