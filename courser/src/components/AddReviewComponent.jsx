import React, { useState } from 'react';
import axios from 'axios';
function AddReviewComponent({ courseCode, onAddReview }) {
    const [userId, setUserId] = useState('');
    const [courseRating, setCourseRating] = useState('');
    const [reviewBody, setReviewBody] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const reviewData = {
                userId,
                courseRating,
                reviewBody,
            };

            await axios.post(`/reviews/course/${courseCode}`, reviewData);
            onAddReview(); // Trigger a refresh of the reviews for the course

            // Clear the form inputs
            setUserId('');
            setCourseRating('');
            setReviewBody('');
        } catch (error) {
            console.error('Error adding review:', error);
        }
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-lg p-8">
                <h2 className="text-2xl font-bold mb-4">Add Review</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block">
                            User ID:
                            <input
                                type="text"
                                value={userId}
                                onChange={(e) => setUserId(e.target.value)}
                                className="border border-gray-300 rounded px-2 py-1 mt-1"
                            />
                        </label>
                    </div>
                    <div className="mb-4">
                        <label className="block">
                            Course Rating:
                            <input
                                type="number"
                                min="1"
                                max="5"
                                value={courseRating}
                                onChange={(e) => setCourseRating(e.target.value)}
                                className="border border-gray-300 rounded px-2 py-1 mt-1"
                            />
                        </label>
                    </div>
                    <div className="mb-4">
                        <label className="block">
                            Review Body:
                            <textarea
                                value={reviewBody}
                                onChange={(e) => setReviewBody(e.target.value)}
                                className="border border-gray-300 rounded px-2 py-1 mt-1"
                            ></textarea>
                        </label>
                    </div>
                    <div className="flex justify-end">
                        <button
                            type="submit"
                            className="bg-blue-500 text-white px-4 py-2 rounded"
                        >
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default AddReviewComponent;
