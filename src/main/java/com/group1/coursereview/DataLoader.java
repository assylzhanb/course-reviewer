package com.group1.coursereview;

import com.group1.coursereview.model.Movie;
import com.group1.coursereview.model.Rating;
import com.group1.coursereview.repository.MovieRepository;
import com.group1.coursereview.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataLoader implements CommandLineRunner {

    private static final String MOVIES_FILE_PATH = "data/movies.csv";
    private static final String RATINGS_FILE_PATH = "data/ratings.csv";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadMovies();
        loadRatings();
    }

    private void loadMovies() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Movie movie = new Movie();
            movie.setId(data[0]);
            movie.setTitle(data[1]);
            movie.setGenres(data[2]);
            movieRepository.save(movie);
        }
        reader.close();
    }

    private void loadRatings() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(RATINGS_FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Rating rating = new Rating();
            rating.setUserId(data[0]);
            rating.setMovieId(data[1]);
            rating.setRating((int) Double.parseDouble(data[2]));
            rating.setTimestamp(Long.parseLong(data[3]));
            try {
                ratingRepository.save(rating);
            } catch (Exception e) {
                System.out.println("Error while saving rating: " + e.getMessage());
            }
        }
        reader.close();
    }
}