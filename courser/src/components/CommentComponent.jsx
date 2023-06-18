import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';

function CommentComponent({ reviewId, showComment, toggleComment }) {
    const [comments, setComments] = useState([]);
    const [commentText, setCommentText] = useState('');

    const fetchComments = useCallback(async () => {
        try {
            const response = await axios.get(`/reviews/${reviewId}/comments`);
            setComments(response.data);
        } catch (error) {
            console.error('Error fetching comments:', error);
        }
    }, [reviewId]);

    useEffect(() => {
        fetchComments();
    }, [fetchComments]);

    const addComment = async () => {
        try {
            const newComment = {
                text: commentText,
            };

            await axios.post(`/reviews/${reviewId}/comments`, newComment);

            setCommentText('');
            fetchComments();
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };

    const deleteComment = async (commentId) => {
        try {
            await axios.delete(`/reviews/${reviewId}/comments/${commentId}`);
            fetchComments();
        } catch (error) {
            console.error('Error deleting comment:', error);
        }
    };

    const handleToggleComment = () => {
        toggleComment();
    };

    return (
        <div className="p-4">
            <h3 className="text-xl font-semibold mb-4">Comments</h3>
            {showComment && (
                <div>
                    {comments.length > 0 ? (
                        comments.map((comment) => (
                            <div key={comment.commentId} className="mb-4 p-4 border rounded flex items-center">
                                <p className="text-base flex-grow">{comment.text}</p>
                                <button
                                    onClick={() => deleteComment(comment.commentId)}
                                    className="text-red-500 font-semibold hover:text-red-700 ml-4"
                                >
                                    Delete
                                </button>
                            </div>
                        ))
                    ) : (
                        <p>No comments yet.</p>
                    )}
                    <div className="flex items-center mt-4">
                        <input
                            type="text"
                            value={commentText}
                            onChange={(e) => setCommentText(e.target.value)}
                            className="border border-gray-300 rounded px-3 py-2 mr-2 w-1/2 focus:outline-none focus:border-blue-500"
                            placeholder="Add a comment..."
                        />
                        <button
                            onClick={addComment}
                            className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                        >
                            Add Comment
                        </button>
                    </div>
                </div>
            )}
            <button
                onClick={handleToggleComment}
                className="text-gray-500 mt-4 font-semibold hover:text-gray-700"
            >
                Close
            </button>
        </div>
    );
}

export default CommentComponent;
