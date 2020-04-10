package ca.on.gov.jus.micro.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ca.on.gov.jus.micro.dao.PersonDao;
import ca.on.gov.jus.micro.model.Person;

@Service
public class PersonService {
	private final PersonDao personDao; 
	
	
	/** ------ DataSource ------
	 * Static DB : Qualifier "fakeDao"
	 * POSTGRES  : Qualifier "postgres"
	 */
	@Autowired
	public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
		super();
		this.personDao = personDao;
	}

	public int addPerson(Person person) {
		return personDao.insertPerson(person);
	}

	public List<Person> getAllPeople(){
		return personDao.selectAllPeople();
	}
	
	public Optional<Person> getPersonById(UUID id){
		return personDao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}
	
	public int updatePerson(UUID id, Person newPerson) {
		return personDao.updatePersonById(id, newPerson);
	}
}
