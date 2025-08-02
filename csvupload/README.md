# CSV Upload Validator - Spring Boot Project

## Project Overview

This project is a Spring Boot-based backend REST API that allows uploading of a CSV file containing user records.  
The application validates each record in the file for:

Required fields: `name` and `date of birth`
Missing values in any field
Invalid date format (must be `YYYY-MM-DD`)

If errors exist, a detailed row-wise error response is returned in JSON format.

## Technologies Used

 Java 17  
 Spring Boot 3.x  
 Maven  
 Apache Commons CSV  
 Postman (for testing)

## Sample CSV File Format

Create a file named `sample_users.csv` with this structure:

name,dateOfBirth
Vineet Shrivastava,2000-01-01
John Doe,1995-12-31

# How to Run the Project

### Prerequisites:
 JDK 17+
 Maven
 IDE Eclipse

## Steps to Run:
1. Open the project in Eclipse as a Maven project.

2. Run the main application class ( with `@SpringBootApplication` annotation).

3. Open Postman and test the API:
    **Method**: `POST`
    **Endpoint**: `http://localhost:8084/api/csv/upload`
    **Body type**: `form-data`
      **Key**: `file`
      **Value**: `sample_users.csv` file*

### API Response Example (in case of validation errors)

```json
 "status": "error",
    "errors": [
        {
            "row": 4,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 5,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 6,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 7,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 8,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 10,
            "message": "DateOfBirth format should be yyyy-MM-dd"
        },
        {
            "row": 11,
            "message": "DateOfBirth is missing"
        },
        {
            "row": 12,
            "message": "Name is missing"
        }
    ]
```


