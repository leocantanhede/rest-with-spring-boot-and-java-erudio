package br.com.erudio.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender(), new Date());
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 person) {
		Person entity = new Person(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender());
		return entity;
	}
	
}