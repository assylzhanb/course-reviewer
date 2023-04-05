package com.group1.coursereview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.group1.coursereview.model.Rating;
import com.group1.coursereview.repository.MovieRepository;
import com.group1.coursereview.repository.RatingRepository;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/")
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/{somerating}")
    public List<Map> getMoviesByRating(@PathVariable int somerating) {
        if (somerating < 1 || somerating > 5) {
            throw new IllegalArgumentException("Invalid rating. Rating should be between 1 and 5.");
        }

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("rating").gte(somerating)),
                Aggregation.group("movieId").avg("rating").as("averageRating"),
                Aggregation.lookup("movies", "_id", "_id", "movies"),
                Aggregation.unwind("movies"),
                Aggregation.match(Criteria.where("averageRating").gte(somerating)),
                Aggregation.group("movies._id")
                        .first("movies._id").as("id")
                        .first("movies.title").as("title")
                        .first("movies.genres").as("genres")
                        .avg("averageRating").as("averageRating"),
                Aggregation.sort(Sort.Direction.ASC, "id")
        );

        AggregationResults<Map> results = mongoTemplate.aggregate(agg, Rating.class, Map.class);
        return results.getMappedResults();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
    @PostMapping("/")
    public Rating addRating(@RequestBody Rating rating) {
        mongoTemplate.insert(rating);
        return rating;
    }
    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating rating) {
        rating.setId(id);
        mongoTemplate.save(rating);
        return rating;
    }
    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable String id) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), Rating.class);
    }
}
