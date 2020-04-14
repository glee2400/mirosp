package ca.on.gov.jus.micro.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ca.on.gov.jus.micro.model.CatalogItem;
import ca.on.gov.jus.micro.model.UserRating;
import ca.on.gov.jus.micro.service.MovieInfo;
import ca.on.gov.jus.micro.service.UserRatingInfo;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogResource {
	
	/* RestTemplate will be replaced by WebClient soon */
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
    private WebClient.Builder webClientBuilder;
	/* Load all service instances, ports, ids, names */
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	MovieInfo movieInfo;
	@Autowired
	UserRatingInfo userRatingInfo;
	
	/* 
	 * 1. Calling --> Erureka sever to discover service by name via restTemplate
	 * 2. Add Hystrix --> CircuitBreaker to monitor the bad threads 
	 */
	
	@RequestMapping("/{userId}")
	//@HystrixCommand (fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//Hystrix inside another class1
		UserRating ratings = userRatingInfo.getUserRating(userId);
		
		//--- Version 1 ---
		//return Collections.singletonList(new CatalogItem("Transformers","test",4));
        
        return ratings.getUserRating().stream()
                .map(rating -> {
                	//Hystrix inside another class2
                	return movieInfo.getCatalogItem(userId, rating);
                })
                .collect(Collectors.toList());
	}
	
	
	/*  
	 * Overall method Remote microservice Fallback method 
	 */
	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
		
		return Arrays.asList(new CatalogItem("No movies", "", 0));
	}
}
