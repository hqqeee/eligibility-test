# Eligibility Project

## Prerequisites

Before starting, ensure you have the following installed on your machine:

- **Java 17** or higher
- **Gradle** 
- **Docker** for containerization
- **SQLite**

## Setup Instructions

### 1. Initialize the SQLite Database
```bash
sqlite3 src/main/resources/db/eligibility.db < data.sql
```
This will run the data.sql script to create the necessary tables and insert some sample data into the database.

### 2. Build the Docker Image
To create a Docker image for the application, use the Jib plugin which is integrated into the build.gradle file. This will build the Docker image without needing a Dockerfile.
```bash
./gradlew clean jibDockerBuild
```
### 3. Run the Docker Container

After the image is built, you can run the Docker container using the following command:
```bash
docker run -p 8080:8080 eligibility:0.0.1-SNAPSHOT
```
### 4. Test application
```bash
curl -X GET "http://localhost:8080/api/eligibility/check?employe_code=123&member_status=employee&employee_id=44000100&employee_date_of_birth=1979-01-09" \
-H "Authorization: Bearer eligibility"
```
