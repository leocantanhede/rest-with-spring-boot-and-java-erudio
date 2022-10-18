package br.com.erudio.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
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
		this.logger.info("Creating one person!");
		return DozerMapper.parseToObject(this.repository.save(DozerMapper.parseToObject(person, Person.class)), PersonVO.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		this.logger.info("Creating one person with V2!");
		return this.mapper.convertEntityToVo(this.repository.save(this.mapper.convertVoToEntity(person)));
	}
	
	public PersonVO update(PersonVO person) {
		this.logger.info("Updating one person!");
		Person entity = this.repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BeanUtils.copyProperties(DozerMapper.parseToObject(person, Person.class), entity, "id");
		return DozerMapper.parseToObject(this.repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		this.logger.info("Deleting one person!");
		this.repository.deleteById(id);
	}
	
	public PersonVO findById(Long id) {
		this.logger.info("Finding one person!");
		return DozerMapper.parseToObject(this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")), PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		this.logger.info("Finding all people!");
		return DozerMapper.parseToListObjects(this.repository.findAll(), PersonVO.class);
	}
	
}