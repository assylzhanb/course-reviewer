package com.group1.coursereview.controller;

import com.group1.coursereview.model.Rating;
import com.group1.coursereview.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") String id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        Rating newRating = ratingRepository.save(rating);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") String id, @RequestBody Rating rating) {
        Optional<Rating> ratingData = ratingRepository.findById(id);

        if (ratingData.isPresent()) {
            Rating _rating = ratingData.get();
            _rating.setUserId(rating.getUserId());
            _rating.setMovieId(rating.getMovieId());
            _rating.setRating(rating.getRating());
            return new ResponseEntity<>(ratingRepository.save(_rating), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable("id") String id) {
        try {
            ratingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

