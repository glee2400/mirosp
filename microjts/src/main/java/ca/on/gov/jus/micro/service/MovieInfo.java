package ca.on.gov.jus.micro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import ca.on.gov.jus.micro.model.CatalogItem;
import ca.on.gov.jus.micro.model.Movie;
import ca.on.gov.jus.micro.model.Rating;

@Service
public class MovieInfo {

	/* RestTemplate will be replaced by WebClient soon */
	@Autowired
    private RestTemplate restTemplate;

	/*
	 * Remote method 1: Get Movie Info
	 */
	@HystrixCommand (fallbackMethod = "getFallbackCatalogItem",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value ="2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value ="5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value ="50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value ="5000")
			},
			/*
			 *  Bulkhead Pattern to defined thread-pool for remote calls  
			 */
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize", value="20"),
					@HystrixProperty(name="maxQueueSize", value="10"),
			}
			)
	public CatalogItem getCatalogItem(String userId, Rating rating) {
		//For each movies ID, call movie info service and get details
		Movie movie = restTemplate.getForObject("http://micro-case-api-service/movies/" + rating.getMovieId(), Movie.class);
		
		//
		//-- User WebClient Builder another way to invoke remove Micro-services --
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
		return new CatalogItem(movie.getName(), "Description -- 8080 -- "+userId, rating.getRating());
	}
	
	
	/* Fallback method for Hystrix Proxy */
	public CatalogItem getFallbackCatalogItem(String userId, Rating rating) {
		return new CatalogItem("Movie name not found", "Description -- 8080 -- "+userId, rating.getRating());

	}
	
}
