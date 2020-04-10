package ca.on.gov.jus.micro.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.jus.micro.model.Movie;

@RestController
@RequestMapping("/cases")
public class CaseResource {

	@RequestMapping ("/{caseId}")
	public Movie getMovieInfo(@PathVariable("caseId") String movieId) {
		return new Movie(movieId, "Glee test -- Case");
	}
}
