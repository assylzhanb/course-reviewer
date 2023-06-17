import React, { useEffect, useState } from 'react';
import axios from 'axios';

const renderReviews = (reviews) => {
    return (
        <ul>
            {reviews.map((review) => (
                <li key={review.reviewId}>
                    <p>User ID: {review.userId}</p>
                    <p>Rating: {review.courseRating}</p>
                    <p>Review: {review.reviewBody}</p>
                </li>
            ))}
        </ul>
    );
}

function CourseComponent() {
    const [course, setCourse] = useState(null);
    const [reviews, setReviews] = useState(null);


    useEffect(() => {
        fetchCourse();
    }, []);

    const fetchCourse = async () => {
        try {
            const response = await axios.get('/courses/byCode/CSE33101');

            setCourse(response.data);
            const response2 = await axios.get(`/reviews/course/` + response.data.courseCode);
            setReviews(response2.data);
            console.log(response2.data);
        } catch (error) {
            console.error('Error fetching course:', error);
        }
    };

    return (
        <div>
            {course ? (
                <div>
                    <h2>Course Details</h2>
                    <p>Title: {course.courseTitle}</p>
                    <p>Code: {course.courseCode}</p>
                    <div>Reviews</div>
                    {reviews ? (
                        renderReviews(reviews)
                    ) : (
                        <p>Loading reviews...</p>
                    )}
                </div>


            ) : (
                <p>Loading course...</p>
            )}
        </div>
    );
}

export default CourseComponent;
