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
| Assylzhan Bakhtiyar  | Team Lead |
| Aidana Saparbai Kyzy | Tester    |
| Myrat Bayramov       | Debugger  |

## Some examples input/output:

Input:

```
curl -X GET http://localhost:8080/employees
```

Output:

```
[] //Because it's initially empty. You have to first make POST.
```

Input:

```
curl -X POST http://localhost:8080/employees -H "Content-type:application/json" -d '{"name": "Samwise Gamgee", "role":"gardener"}'
// Please note that `Content-type:...` must be with double quotes instead of single (which was provided in the handout)
```

Output:

```
{"id":1,"name":"Samwise Gamgee","role":"gardener"}
```

Input:

```
curl -X PUT http://localhost:8080/employees/1 -H "Content-type:application/json" -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'
// Please note that `Content-type:...` must be with double quotes instead of single (which was provided in the handout)
```

Output:

```
{"id":1,"name":"Samwise Gamgee","role":"ring bearer"}
```

Now making GET request will result in:

```
[{"id":1,"name":"Samwise Gamgee","role":"ring bearer"}]
```

Input:

```
curl -X GET http://localhost:8080/ratings/0
```

Output:

```
{"error":"Invalid rating. Rating should be between 1 and 5."}
```

Input:

```
curl -X GET http://localhost:8080/ratings/4
```

Output:

```
[
    {"_id":"1","id":"1","title":"\"Toy Story (1995)\"","genres":"Animation|Children's|Comedy","averageRating":4.522016592214422},
    {"_id":"10","id":"10","title":"\"GoldenEye (1995)\"","genres":"Action|Adventure|Thriller","averageRating":4.262845849802371},
    ...
]
```

Input:

```
curl -X POST http://localhost:8080/ratings/ -H "Content-type:application/json" -d '{"userId":1, "movieId":"2","rating":3}'
// In our case, POST can be done with these keys: 'userId', 'movieId', 'rating'.
```

Example Output:

```
{"userId":"1","movieId":"2","rating":3}
```

Input:

```
curl -X PUT http://localhost:8080/ratings/100 -H "Content-type:application/json" -d '{"userId":1, "movieId":"2","rating":4}'
```

Output:

```
{"userId":"1","movieId":"2","rating":4}
```
