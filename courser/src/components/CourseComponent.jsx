import React, { useEffect, useState } from 'react';
import axios from 'axios';
import AddReviewComponent from './AddReviewComponent';

function CourseComponent() {
    const [courseCode, setCourseCode] = useState('');
    const [course, setCourse] = useState(null);
    const [reviews, setReviews] = useState(null);
    const [loadingCourse, setLoadingCourse] = useState(false);
    const [loadingReviews, setLoadingReviews] = useState(false);
    const [showAddReview, setShowAddReview] = useState(false);

    const toggleAddReview = () => {
        if (course === null) {
            setShowAddReview((prevState) => !prevState);

        }
    };

    useEffect(() => {
        if (courseCode) {
            fetchCourse(courseCode);
        }
    }, [courseCode]);

    const fetchCourse = async (code) => {
        try {
            setLoadingCourse(true);

            const response = await axios.get(`/courses/byCode/${code}`);
            setCourse(response.data);

            setLoadingCourse(false);
            setLoadingReviews(true);

            setReviews(null); // Clear the reviews state before fetching new reviews

            const response2 = await axios.get(`/reviews/course/${response.data.courseCode}`);
            setReviews(response2.data);

            setLoadingReviews(false);
        } catch (error) {
            console.error('Error fetching course:', error);
            setLoadingCourse(false);
            setLoadingReviews(false);
        }
    };

    const handleEditReview = async (reviewId) => {
        try {
            const response = await axios.get(`/reviews/${reviewId}`);
            const reviewData = response.data;
            console.log('Edit Review:', reviewData);
        } catch (error) {
            console.error('Error fetching review:', error);
        }
    };

    const handleDeleteReview = async (reviewId) => {
        try {
            await axios.delete(`/reviews/${reviewId}`);
            console.log('Delete Review:', reviewId);
            // Trigger a refresh by fetching the updated list of reviews for the course
            fetchCourse(courseCode);
        } catch (error) {
            console.error('Error deleting review:', error);
            console.log('Delete Review:', reviewId);
        }
    };

    const renderReviews = (reviews) => {
        return (
            <div className="grid gap-4">
                {reviews.map((review) => (
                    <div key={review.reviewId} className="border rounded p-4">
                        <p className="text-lg font-semibold">User ID: {review.userId}</p>
                        <p className="text-lg">Rating: {review.courseRating}</p>
                        <p className="text-lg">Review: {review.reviewBody}</p>
                        <div className="mt-4 flex space-x-4">
                            <button
                                className="bg-blue-500 text-white px-4 py-2 rounded"
                                onClick={() => handleEditReview(review.reviewId)}
                            >
                                Edit
                            </button>
                            <button
                                className="bg-red-500 text-white px-4 py-2 rounded"
                                onClick={() => handleDeleteReview(review.reviewId)}
                            >
                                Delete
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (courseCode) {
            fetchCourse(courseCode);
            setShowAddReview(false);
        }
    };

    return (
        <div className="container mx-auto py-8">
            <form onSubmit={handleSubmit} className="mb-4">
                <label className="mr-2">
                    Enter Course Code:
                    <input
                        type="text"
                        value={courseCode}
                        onChange={(e) => setCourseCode(e.target.value)}
                        className="border border-gray-300 rounded px-2 py-1"
                    />
                </label>
                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
                    Get Reviews
                </button>
            </form>

            <div className="mb-4">
                {loadingCourse ? (
                    <p>Loading course...</p>
                ) : course ? (
                    <div className="border rounded p-4">
                        <h2 className="text-2xl font-bold mb-2">Course Details</h2>
                        <p className="text-lg">
                            <span className="font-semibold">Title:</span> {course.courseTitle}
                        </p>
                        <p className="text-lg">
                            <span className="font-semibold">Code:</span> {course.courseCode}
                        </p>
                        <p className="text-lg">
                            <span className="font-semibold">School:</span> {course.school}
                        </p>
                        <p className="text-lg">
                            <span className="font-semibold">Professor:</span> {course.professorName}
                        </p>
                        <p className="text-lg">
                            <span className="font-semibold">Program:</span> {course.program}
                        </p>
                        <p className="text-lg">
                            <span className="font-semibold">Prerequisite:</span>{' '}
                            {course.prerequisite || 'None'}
                        </p>
                        {course ? (
                            <button
                                className="bg-blue-500 text-white px-4 py-2 rounded"
                                onClick={toggleAddReview}
                            >
                                Add Review
                            </button>
                        ) : null}
                    </div>
                ) : (
                    <p>Enter a course code to get the details and reviews.</p>
                )}
            </div>

            {loadingReviews ? (
                <p>Loading reviews...</p>
            ) : reviews ? (
                <div className="mt-4">
                    <div className="mb-2">Reviews</div>
                    {renderReviews(reviews)}
                </div>
            ) : (
                <p>No reviews found.</p>
            )}



            {showAddReview && (
                <AddReviewComponent courseCode={courseCode} onAddReview={fetchCourse} />
            )}
        </div>
    );
}

export default CourseComponent;
