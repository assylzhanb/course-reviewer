package com.group1.coursereview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection="ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {

    private ObjectId id;
    @Id
    private String userId;
    private String movieId;
    private double rating;
    private long timeStamp;
    public Ratings(String userId, long timeStamp, String movieId, double rating){
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }
    public long getTimeStamp() {
        return this.timeStamp;
    }
    public String getUserId() {
        return this.userId;
    }

    public String getMovieId() {

        return this.movieId;
    }

    public double getRating() {
        return this.rating;
    }


}
