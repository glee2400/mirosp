package ca.on.gov.jus.micro.api;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.jus.micro.model.Rating;
import ca.on.gov.jus.micro.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
    
    @RequestMapping ("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
		//Mock up rating data for a user, instead of from remove Rating Micro-service
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}