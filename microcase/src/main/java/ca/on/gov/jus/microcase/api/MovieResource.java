package ca.on.gov.jus.microcase.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.jus.microcase.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping ("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "Service -- 8082 {Input movies/moviesId} -- Actual Movie "+movieId);
	}
}
