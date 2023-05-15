# Course-Reviewer

## Project Description

This is Group 1's project for CSE364 course. This is a course reviewing website which will be designed specifically for UNIST students.

## How to run:

```
docker build -t image_name /path/to/Dockerfile
docker run -it image_name
```

Inside the docker container, run:

```
root@containerID$ sh run.sh
```

## Team Members and Roles:

| Name                 | Role      |
| -------------------- | --------- |
| Assylzhan Baktiyar   | Team Lead |
| Aidana Saparbai Kyzy | Tester    |
| Myrat Bayramov       | Debugger  |

# Part II

## Description of features
### Basically we interpreted all the features that we wrote in the proposal: 
-  User authentication (trivial as it was said in piazza)
-  Lookup for courses 
-  Write a review
-  Commenting

## How we got the data
We made an SQL query on portal to get xlsx  [file](/data/courses.csv) of every course available, with corresponding information. 
Then, based on that list we created another list about every [professor](/data/professors.csv) in UNIST. So, we got original data from the portal. Other data that will be used here like **comments and reviews** will be created along the way with this tutorial. 
# 1. User  authentification 
## - Signup
```
curl -X POST -H "Content-Type: application/json" -d '{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "password123",
  "studentId": "12345678"
}' http://localhost:8080/signup
```

This will return 

```
Signup successful
```
Notice that you can no longer sign up with jogndoe@example.com because it is already in the data base. 
##  Login
```
curl -X POST -H "Content-Type: application/json" -d '{
  "email": "johndoe@example.com",
  "password": "password123"
}' http://localhost:8080/login
```
```
#results
Logged in successfully
```
(Edge cases were all covered in the tests, here we are just showing some correct functionality)

```
curl -X POST http://localhost:8080/logout
```
```
#results
Logged out successfully
```

# 2. Look up for courses
## Look up by name
```
curl -X GET http://localhost:8080/courses/byName/Intro%20to%20Algorithms
```
```
#results 
[{"id":"645e4b32c5d7e11bb36e1f13","school":"Department of Computer Science and Engineering","courseCode":"CSE33101","courseTitle":"Intro to Algorithms","professorName":"Seulki Lee","program":"Undergraduate Course","prerequisite":"Data Structures"}]
```
For references look for [courses](data/courses.csv) file where every existing course is listed. 

## Look up by course code
```
curl -X GET http://localhost:8080/courses/byCode/CSE22101
```
```
#results 
{"id":"645e4b29c5d7e11bb36e1d67","school":"Department of Computer Science and Engineering","courseCode":"CSE22101","courseTitle":"Data Structures","professorName":"TAEHWAN KIM","program":"Undergraduate Course","prerequisite":null}
```
# 3. Write a review
```
curl -X GET http://localhost:8080/reviews/course/CSE33101
```
```
#result
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 646230a292d2b127f4fffd39
User ID: 123
Review Body: Great course!
Course Code: CSE33101
Course Rating: 4
Created At: 2023-05-15T22:16:18.706
----------
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 646244323112e900b3f880a6
User ID: {userId}
Review Body: This course sucks
Course Code: CSE33101
Course Rating: 1
Created At: 2023-05-15T23:39:46.070
----------
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 6462444f3112e900b3f880a7
User ID: {userId}
Review Body: I DONT LIKE THE course
Course Code: CSE33101
Course Rating: 1
Created At: 2023-05-15T23:40:15.567
----------
```

## Create review
```
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 646230a292d2b127f4fffd39
User ID: 123
Review Body: Great course!
Course Code: CSE33101
Course Rating: 4
Created At: 2023-05-15T22:16:18.706
----------
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 646244323112e900b3f880a6
User ID: {userId}
Review Body: This course sucks
Course Code: CSE33101
Course Rating: 1
Created At: 2023-05-15T23:39:46.070
----------
Course Name: Intro to Algorithms
Count of reviews: 8
Review ID: 6462444f3112e900b3f880a7
User ID: {userId}
Review Body: I DONT LIKE THE course
Course Code: CSE33101
Course Rating: 1
Created At: 2023-05-15T23:40:15.567
----------
```
```
#results 
Review posted!
```

## Update review
```
curl -X PUT -H "Content-Type: application/json" -d '{"courseRating": 5, "reviewBody": "Excellent course!"}' http://localhost:8080/reviews/6462444f3112e900b3f880a7
```
```
#results
Changed the review to Excellent course!
```

## Delete the review
```
curl -X DELETE http://localhost:8080/reviews/{reviewId}
```
```
Review deleted successfully
```
# 4. Commenting
## Get comments
```
curl -X GET http://localhost:8080/reviews/750210ed-2608-4faf-abe1-c389c39c714d/comments
```
```
#results 
[{"id":"6462312892d2b127f4fffd3a","reviewId":"750210ed-2608-4faf-abe1-c389c39c714d","userId":null,"text":"This course helped me a lot!"},{"id":"64623f3e3112e900b3f880a4","reviewId":"750210ed-2608-4faf-abe1-c389c39c714d","userId":null,"text":"This course helped me a lot!"}]
```
## Add comments
```
curl -X POST -H "Content-Type: application/json" -d '{"text":"This is a comment."}' http://localhost:8080/reviews/750210ed-2608-4faf-abe1-c389c39c714d/comments
```
```
#results 
{"id":"646246903112e900b3f880a9","reviewId":"750210ed-2608-4faf-abe1-c389c39c714d","userId":null,"text":"This is a comment."}
```




