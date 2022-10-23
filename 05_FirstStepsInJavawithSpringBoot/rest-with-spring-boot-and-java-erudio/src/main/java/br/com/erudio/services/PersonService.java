package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	private final Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;

	@Autowired
	private PersonMapper mapper;
	
	public PersonVO create(PersonVO person) {
		if (person == null)
			throw new RequiredObjectIsNullException();
		this.logger.info("Creating one person!");
		PersonVO vo = DozerMapper.parseToObject(this.repository.save(DozerMapper.parseToObject(person, Person.class)), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		this.logger.info("Creating one person with V2!");
		return this.mapper.convertEntityToVo(this.repository.save(this.mapper.convertVoToEntity(person)));
	}
	
	public PersonVO update(PersonVO person) {
		if (person == null)
			throw new RequiredObjectIsNullException();
		this.logger.info("Updating one person!");
		Person entity = this.repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BeanUtils.copyProperties(DozerMapper.parseToObject(person, Person.class), entity, "id");
		PersonVO vo = DozerMapper.parseToObject(this.repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		this.logger.info("Deleting one person!");
		this.repository.deleteById(id);
	}
	
	public PersonVO findById(Long id) {
		this.logger.info("Finding one person!");
		PersonVO vo = DozerMapper.parseToObject(this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public List<PersonVO> findAll() {
		this.logger.info("Finding all people!");
		List<PersonVO> persons = DozerMapper.parseToListObjects(this.repository.findAll(), PersonVO.class);
		persons.stream().forEach(person -> {
			person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		});
		return persons;
	}
	
}