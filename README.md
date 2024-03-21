# CoderHack
CoderHack is a RESTful API service developed using Spring Boot to manage the leaderboard for a coding platform. It utilizes MongoDB to persist the data and provides endpoints for various leaderboard management operations.

# Problem Description
While coding platforms usually host multiple contests while maintaining numerous leaderboards, CoderHack focuses on managing the leaderboard of a specific contest. It assumes the platform has only one contest with a single leaderboard and gives virtual awards to users called Badges based on their score.

# Installation Guide
## Prerequisites
- :gear: [MongoDB](https://docs.mongodb.com/manual/installation/) installed on your local machine
- :computer: [Spring Boot](https://spring.io/projects/spring-boot) and [Java](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) version 8 or higher
### Steps
- Clone the repository by using the command given below:
``` Shell
    git clone https://github.com/bhooshanmate/CoderHack.git
    cd CoderHack
```
- Run the project by using the following command:
``` Shell
    mvn spring-boot:run
```
- Connect to the API using Postman on port 8080
``` Shell
    http://localhost:8080
```     

# Usage
### API Endpoints
| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| GET | /users | To get all participants |
| GET | /users/{userId} | To get a particular participant |
| POST | /users | To create new participants |
| PUT | /users/{userId}/{updateBy} | To update scores |
| DELETE | /users | To remove participants from contest |

### Checkout this Postman collections - https://www.postman.com/satellite-specialist-32280848/workspace/coderhack-crio
