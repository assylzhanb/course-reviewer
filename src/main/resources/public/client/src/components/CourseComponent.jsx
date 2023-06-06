import React, { useEffect, useState } from 'react';
import axios from 'axios';

function CourseComponent() {
    const [course, setCourse] = useState(null);

    useEffect(() => {
        fetchCourse();
    }, []);

    const fetchCourse = async () => {
        try {
            const response = await axios.get('http://localhost:8080/courses/byCode/CSE33101'); // Update the URL with your API endpoint
            setCourse(response.data);
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
                    {/* Display other course details as needed */}
                </div>
            ) : (
                <p>Loading course...</p>
            )}
        </div>
    );
}

export default CourseComponent;
