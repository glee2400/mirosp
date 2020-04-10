package ca.on.gov.jus.micro;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.on.gov.jus.micro.model.Person;


@ExtendWith ({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class MicrojtsApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	List<Person> persons = null;
	
	@Test
	void contextLoads() {
	}
	
	/*
	 * 
	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext,
			RestDocumentationContextProvider restDocumentation
			) {
		this.mokMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();
		persons = Stream.of(new Person(UUID.randomUUID(), "Glee Test1"),
						new Person(UUID.randomUUID(), "Glee Test2"))
				.collect(Collectors.toList());
	}
	
	@Test
	public void testAddPerson() throws Exception{
		String personJson = new ObjectMapper().writeValueAsString(persons);
		mockMvc.perform(post(urlTemplate:"/api/v1/person")
				.content(personJson)
				.contentType("application/json")).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(personJson)).andDo(
						document(identifier:"{methodName}"), preProcessRequest(prettyPrint()), 
						preProcessResponse(prettyPrint()));
	}
	
	@Test
	public void testGetAllPeople() throws Exception{
		mockMvc.perform(get(urlTemplate:"/api/v1/person")
				.contentType("application/json")).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(persons)));
	}
	
	*
	*/
}
