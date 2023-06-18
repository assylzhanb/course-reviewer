import React from 'react';
import AddCommentComponent from './AddCommentComponent';
function CommentComponent({ comments, reviewId }) {
    return (
        <div className="mt-4">
            <h3 className="text-lg font-semibold mb-2">Comments:</h3>
            {comments.length > 0 ? (
                <ul className="list-disc list-inside">
                    {comments.map((comment) => (
                        <li key={comment.commentId} className="text-lg">{comment.text}</li>
                    ))}
                </ul>
            ) : (
                <p>No comments found.</p>
            )}
            <AddCommentComponent reviewId={reviewId} />
        </div>
    );
}

export default CommentComponent;
