package com.group1.coursereview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group1.coursereview.Movie;


import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMoviesByRating(double rating) {
        return movieRepository.findByAverageRatingGreaterThanEqual(rating);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
