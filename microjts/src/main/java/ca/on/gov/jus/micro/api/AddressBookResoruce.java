package ca.on.gov.jus.micro.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.jus.micro.model.Contact;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class AddressBookResoruce {
	
	ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();
	
	@GetMapping ("/{id}")
	@ApiOperation( value="Finds Contacts by id",
			notes = "Provide an id to look up specific contact from the address book",
			response = Contact.class
			)
	public Contact getContact(@ApiParam(value="ID value for the contact you need to retrieve", required = true)
						@PathVariable String id) {
		return contacts.get(id);
	}
	
	@GetMapping("/")
	@ApiOperation( value="Finds all contacts in address book",
			notes = "Provide all contacts from the address book",
			response = List.class
			)
	public List<Contact> getAllContacts(){
		return new ArrayList<Contact>(contacts.values());
	}

	@PostMapping("/")
	@ApiOperation( value="Add a contact in address book",
			notes = "Add a contact in the address book",
			response = Contact.class
			)
	public Contact addContact(@RequestBody Contact contact) {
		contacts.put(contact.getId(), contact);
		return contact;
	}
}
