# Course-Reviewer

## Project Description

This is a course reviewing website which will be designed specifically for UNIST students.

## How to run:

```
docker build -t image_name /path/to/Dockerfile
docker run -p 8080:8080 -it image_name
```

Now you can open http://localhost:8080/ on your browser and you will see the result

## Home Page

The home page of Course-Reviewer displays a search bar where you can enter a course code to view its reviews. Follow these steps to search for course reviews:

1. On the home page, you will see a search bar with the label "Enter Course Code."
2. Enter the course code you want to search for (e.g., CSE33101) in the search bar.
3. Click the "Get Reviews" button.
4. The page will display the course details, including its title, code, school, professor, program, and prerequisite (if any).
5. You will also see a list of reviews for the course if any exist.
6. Each review displays the user ID, rating, and review body.
7. You can edit or delete a review by clicking the corresponding buttons.
8. To add a new review for the course, click the "Add Review" button.

## Add Review

To add a new review for a course, follow these steps:

1. After searching for a course and viewing its details, click the "Add Review" button.
2. A form will appear where you can enter your review information.
3. Fill in the required fields, including the rating (from 1 to 5) and the review body.
4. Click the "Submit" button to add your review.
5. The page will refresh, and your review will appear in the list of reviews for the course.

## Edit Review

To edit an existing review, follow these steps:

1. After searching for a course and viewing its details, locate the review you want to edit.
2. Click the "Edit" button next to the review.
3. The review form will appear with the existing review information pre-filled.
4. Modify the rating and review body as desired.
5. Click the "Submit" button to save your changes.
6. The page will refresh, and the updated review will appear in the list of reviews for the course.

## Delete Review

To delete a review, follow these steps:

1. After searching for a course and viewing its details, locate the review you want to delete.
2. Click the "Delete" button next to the review.
3. A confirmation dialog will appear asking you to confirm the deletion.
4. Click "OK" to proceed with the deletion.
   The review will be removed from the list of reviews for the course.

## Comment on a Review

To add a comment to a review, follow these steps:

1. After searching for a course and viewing its details, locate the review you want to comment on.
2. Click the "Comment" button next to the review.
3. A comment section will appear below the review.
4. Enter your comment in the provided input field.
5. Click the "Submit" button to add your comment.
6. The comment will appear below the review.

## View Comments

To view comments on a review, follow these steps:

1. After searching for a course and viewing its details, locate the review you want to view comments on.
2. If there are any comments, a "View Comments" button will be displayed below the review.
3. Click the "View Comments" button to expand the comment section and view all comments.
4. To hide the comments, click the "Hide Comments" button.

# Technical overview

Course-Reviewer combines the following technologies for an efficient user experience:

## Frontend:

- React
- Tailwind CSS
- Axios

## Backend

- Spring Boot
- MongoDB
- Hibernate Validator

## **Deployment**

### Course-Reviewer utilizes a different deployment approach compared to the traditional method of using Tomcat with a WAR file. Instead, the project leverages the frontend-maven-plugin in combination with Docker for deployment.

### Using frontend-maven-plugin, Course-Reviewer ensures that the frontend (React) application is built and bundled during the Maven build process. This eliminates the need for manual frontend build steps and keeps the deployment process unified within the Maven lifecycle. The Dockerfile provided in the previous section takes advantage of the built frontend assets and deploys the entire application on port 8080.
