import React, { useState, useEffect } from 'react';
import axios from 'axios';

function EditReviewComponent({ reviewId, onUpdateReview, toggleEditReview }) {
    const [courseRating, setCourseRating] = useState('');
    const [reviewBody, setReviewBody] = useState('');

    useEffect(() => {
        const fetchReviewData = async () => {
            try {
                const response = await axios.get(`/reviews/${reviewId}`);
                const reviewData = response.data;
                setCourseRating(reviewData.courseRating);
                setReviewBody(reviewData.reviewBody);
            } catch (error) {
                console.error('Error fetching review:', error);
            }
        };

        fetchReviewData();
    }, [reviewId]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const updatedReviewData = {
                courseRating,
                reviewBody,
            };

            await axios.put(`/reviews/${reviewId}`, updatedReviewData);
            onUpdateReview(); // Trigger a refresh of the reviews

            // Close the EditReviewComponent by calling toggleEditReview from the parent component
            toggleEditReview();
        } catch (error) {
            console.error('Error updating review:', error);
        }
    };


    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-lg p-8">
                <h2 className="text-2xl font-bold mb-4">Edit Review</h2>
                <form onSubmit={handleSubmit}>
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
                        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
                            Update
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EditReviewComponent;
