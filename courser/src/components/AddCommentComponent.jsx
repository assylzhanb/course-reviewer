import React, { useState } from 'react';
import axios from 'axios';

function AddCommentComponent({ reviewId }) {
    const [commentText, setCommentText] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const newCommentData = {
                text: commentText,
            };

            await axios.post(`/reviews/${reviewId}/comments`, newCommentData);
            setCommentText('');
            // You can add additional logic here if needed, such as refreshing the comments or updating the UI.
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };

    return (
        <div className="mt-4">
            <h3 className="text-lg font-semibold mb-2">Add Comment:</h3>
            <form onSubmit={handleSubmit}>
                <textarea
                    value={commentText}
                    onChange={(e) => setCommentText(e.target.value)}
                    className="border border-gray-300 rounded px-2 py-1 mt-1"
                    placeholder="Enter your comment"
                ></textarea>
                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded mt-2">
                    Add Comment
                </button>
            </form>
        </div>
    );
}

export default AddCommentComponent;
