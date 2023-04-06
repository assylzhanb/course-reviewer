package com.group1.coursereview.model;

public class MovieDto {
    // THIS CLASS was created to avoid returning unnecessary averageRating value when running
    // curl -X GET localhost:8080/movies/
    private String id;
    private String title;
    private String genres;

    public MovieDto(String id, String title, String genres) {
        this.id = id;
        this.title = title;
        this.genres = genres;
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getGenres(){
        return genres;
    }
}