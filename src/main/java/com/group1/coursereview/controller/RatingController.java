package com.group1.coursereview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.group1.coursereview.model.Rating;
import com.group1.coursereview.repository.MovieRepository;
import com.group1.coursereview.repository.RatingRepository;

@RestController
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/ratings/{somerating}")
    public List<Map> getMoviesByRating(@PathVariable int somerating) {

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

}
