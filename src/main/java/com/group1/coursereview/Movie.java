package com.group1.coursereview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.AbstractList;
import java.util.List;

@Document(collection="movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private ObjectId id;
    @Id
    private String movieId;
    private String title;
    private String genres;

    private List<Ratings> ratingsList;
    public Double getAverageRating() {
        if (ratingsList == null || ratingsList.isEmpty()) {
            return null;
        }
        double sum = 0;
        for (Ratings rating : ratingsList) {
            sum += rating.getRating();
        }
        return sum / ratingsList.size();
    }
    public Movie(String movieID, String title, String genres) {
        this.title = title;
        this.movieId = movieID;
        this.genres = genres;
    }

//    public List<Movie> getMoviesAbove(Integer r){
//        List<Movie> result = new List<Movie>[];
//        return result;
//    }

    public String getMovieId() {
        return this.movieId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getGenres(){
        return this.genres;
    }
}
