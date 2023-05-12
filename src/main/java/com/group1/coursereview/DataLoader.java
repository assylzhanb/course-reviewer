//package com.group1.coursereview;
//
//import com.group1.coursereview.model.Course;
//import com.group1.coursereview.model.Movie;
//import com.group1.coursereview.model.Rating;
//import com.group1.coursereview.repository.CourseRepository;
//import com.group1.coursereview.repository.MovieRepository;
//import com.group1.coursereview.repository.RatingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import com.group1.coursereview.model.Professor;
//import com.group1.coursereview.repository.ProfessorRepository;
//
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private static final String MOVIES_FILE_PATH = "data/movies.csv";
//    private static final String RATINGS_FILE_PATH = "data/ratings.csv";
//
//    private static final String PROFESSORS_FILE_PATH = "data/professors.csv";
//    private static final  String COURSES_FILE_PATH = "data/courses.csv";
//
//
//    @Autowired
//    private ProfessorRepository professorRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private MovieRepository movieRepository;
//
//    @Autowired
//    private RatingRepository ratingRepository;
//
//
//    @Override
//    public void run(String... args) throws Exception {
////        loadMovies();
////        loadRatings();
////        loadProfessors();
////        loadCourses();
//    }
//    private void loadProfessors() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(PROFESSORS_FILE_PATH));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            Professor professor = new Professor();
//            professor.setInstId(data.length > 0 ? data[0] : null);
//            professor.setName(data.length > 1 ? data[1] : null);
//            professor.setContactInfo(data.length > 2 ? data[2] : null);
//            professor.setDepartment(data.length > 3 ? data[3] : null);
//            professor.setPosition(data.length > 4 ? data[4] : null);
//            professor.setEducationalBackground(data.length > 5 ? data[5] : null);
//            professorRepository.save(professor);
//        }
//        reader.close();
//    }
//
//
//    private void loadCourses() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE_PATH));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            Course course = new Course();
//            course.setSchool(data.length > 0 ? data[0] : null);
//            course.setCourseCode(data.length > 1 ? data[1] : null);
//            course.setCourseTitle(data.length > 2 ? data[2] : null);
//            course.setProfessorName(data.length > 3 ? data[3] : null);
//            course.setProgram(data.length > 4 ? data[4] : null);
//            course.setPrerequisite(data.length > 5 ? data[5] : null);
//            courseRepository.save(course);
//        }
//        reader.close();
//    }
//
//
//
//    private void loadMovies() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE_PATH));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            Movie movie = new Movie();
//            movie.setId(data[0]);
//            movie.setTitle(data[1]);
//            movie.setGenres(data[2]);
//            movieRepository.save(movie);
//        }
//        reader.close();
//    }
//
//    private void loadRatings() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(RATINGS_FILE_PATH));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            Rating rating = new Rating();
//            rating.setUserId(data[0]);
//            rating.setMovieId(data[1]);
//            rating.setRating((int) Double.parseDouble(data[2]));
//            rating.setTimestamp(Long.parseLong(data[3]));
//            try {
//                ratingRepository.save(rating);
//            } catch (Exception e) {
//                System.out.println("Error while saving rating: " + e.getMessage());
//            }
//        }
//        reader.close();
//    }
//}