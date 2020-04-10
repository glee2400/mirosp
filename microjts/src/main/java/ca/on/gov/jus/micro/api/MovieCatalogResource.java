package ca.on.gov.jus.micro.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ca.on.gov.jus.micro.model.CatalogItem;
import ca.on.gov.jus.micro.model.Movie;
import ca.on.gov.jus.micro.model.UserRating;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogResource {
	
	/* RestTemplate will be replaced by WebClient soon */
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8080/ratingsdata/users/"+userId, UserRating.class);
		
//		--- Version 1 ---
//		return Collections.singletonList(new CatalogItem("Transformers","test",4));
        
        return ratings.getUserRating().stream()
                .map(rating -> {
                    
                	//For each movies ID, call movie info service and get details
                	Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                	
                	//
                	//-- User WebClient Builder anotehr way to invoke remove Micro-services --
                	//   Chain mechanism to call remove microservice
                	//   Mono is asynchronized call and waiting for response
                	//   Block change the call form asynchronize to synchronize call
                	/*
                	Movie movie = webClientBuilder.build()
                		.get()
                		.uri("http://localhost:8082/movies/" + rating.getMovieId())
                		.retrieve()
                		.bodyToMono(Movie.class)
                		.block();
                	*/
                	
                    //Put them all together
                    return new CatalogItem(movie.getName(), "Description -- 8080 -- Description", rating.getRating());
                })
                .collect(Collectors.toList());
        
	}
}
