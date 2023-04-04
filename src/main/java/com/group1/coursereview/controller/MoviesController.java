package com.group1.coursereview.controller;

import com.group1.coursereview.Movie;
import com.group1.coursereview.MovieRepository;
import com.group1.coursereview.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MoviesController{
//    @Autowired
//    private MovieService movieService;
//    @Autowired
//    private MovieRepository movieRepository;
//
//    @GetMapping("/ratings/{rating}")
//    public ResponseEntity<List<Movie>> getMoviesByRating(@PathVariable("rating") double rating) {
//        List<Movie> movies = movieRepository.findByAverageRatingGreaterThanEqual(rating);
//        if (movies.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(movies, HttpStatus.OK);
//        }
//    }
//    @GetMapping("/{id}")
//    public Movie getMovie(@PathVariable Long id) {
//        Optional<Movie> optionalMovie = movieRepository.findById(String.valueOf(id));
//        if (optionalMovie.isPresent()) {
//            return optionalMovie.get();
//        }
//        return optionalMovie.get();
//    }
//    @PostMapping
//    public void addMovie(@RequestBody Movie movie) {
//        movieService.addMovie(movie);
//    }
//    @PutMapping("/{id}")
//    public void updateMovie(@PathVariable String id, @RequestBody Movie movie) {
//        movie.setMovieId(id);
//        movieService.updateMovie(movie);
//    }
}
