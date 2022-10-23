package br.com.erudio.unit.tests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;
import br.com.erudio.unit.tests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	private MockPerson mock;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mock = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		Person entity = this.mock.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = this.mock.mockVO(1);
		vo.setKey(1L);
		when(this.repository.save(entity)).thenReturn(persisted);
		PersonVO result = this.service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/person/v1/1>;rel=\"self\""));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			this.service.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}

	@Test
	void testUpdate() {
		Person entity = this.mock.mockEntity(1);
		entity.setId(1L);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = this.mock.mockVO(1);
		vo.setKey(1L);
		when(this.repository.findById(1L)).thenReturn(Optional.of(entity));
		when(this.repository.save(entity)).thenReturn(persisted);
		PersonVO result = this.service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/person/v1/1>;rel=\"self\""));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			this.service.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = this.mock.mockEntity(1);
		entity.setId(1L);
		lenient().when(this.repository.findById(1L)).thenReturn(Optional.of(entity));
		this.service.delete(1L);
	}

	@Test
	void testFindById() {
		Person entity = this.mock.mockEntity(1);
		entity.setId(1L);
		when(this.repository.findById(1L)).thenReturn(Optional.of(entity));
		PersonVO vo = this.service.findById(1L);
		assertNotNull(vo);
		assertNotNull(vo.getKey());
		assertNotNull(vo.getLinks());
		assertTrue(vo.toString().contains("</api/person/v1/1>;rel=\"self\""));
		assertEquals("First Name Test1", vo.getFirstName());
		assertEquals("Last Name Test1", vo.getLastName());
		assertEquals("Address Test1", vo.getAddress());
		assertEquals("Female", vo.getGender());
		
	}

	@Test
	void testFindAll() {
		List<Person> entities = this.mock.mockEntityList();
		when(this.repository.findAll()).thenReturn(entities);
		List<PersonVO> people = this.service.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());
		PersonVO vo = people.get(1);
		assertNotNull(vo);
		assertNotNull(vo.getKey());
		assertNotNull(vo.getLinks());
		assertTrue(vo.toString().contains("</api/person/v1/1>;rel=\"self\""));
		assertEquals("First Name Test1", vo.getFirstName());
		assertEquals("Last Name Test1", vo.getLastName());
		assertEquals("Address Test1", vo.getAddress());
		assertEquals("Female", vo.getGender());
	}

}