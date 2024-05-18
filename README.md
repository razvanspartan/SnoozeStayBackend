# StaySnooze
### StaySnooze is a hotel finder and booking platform, developed for the Siemens Java Internship project. The backend is built with Java Spring Boot, providing a robust and scalable server-side application.

## Backend

[You will also need the frontend component](https://github.com/razvanspartan/SnoozeStay)

## API Endpoints
### Add a Hotel

URL: /saveHotel

Method: POST

Request Body:

```json
{
  "id": 1,
  "name": "Hotel Name",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "rooms": [
    {
      "id": 101,
      "roomNumber": 101,
      "type": 1,
      "price": 100.0,
      "isAvailable": true
    }
  ],
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "Great stay!",
      "user": {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "user@example.com",
        "password": "password123"
      }
    }
  ]
}
```

Response:
```json
{
  "id": 1,
  "name": "Hotel Name",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "rooms": [
    {
      "id": 101,
      "roomNumber": 101,
      "type": 1,
      "price": 100.0,
      "isAvailable": true
    }
  ],
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "Great stay!",
      "user": {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "user@example.com",
        "password": "password123"
      }
    }
  ]
}
```
### Add more Hotels

URL: /saveAllHotels

Method: POST

Request Body:

```json
[
{
  "id": 1,
  "name": "Hotel Name",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "rooms": [
    {
      "id": 101,
      "roomNumber": 101,
      "type": 1,
      "price": 100.0,
      "isAvailable": true
    }
  ],
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "Great stay!",
      "user": {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "user@example.com",
        "password": "password123"
      }
    }
  ]
},
  ...
]
```

Response:
```json
{
  "id": 1,
  "name": "Hotel Name",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "rooms": [
    {
      "id": 101,
      "roomNumber": 101,
      "type": 1,
      "price": 100.0,
      "isAvailable": true
    }
  ],
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "Great stay!",
      "user": {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "user@example.com",
        "password": "password123"
      }
    }
  ]
},
...
]
```
### Add a Review

URL: /saveReview

Method: POST

Request Body:

```json
{
  "id": 1,
  "rating": 5,
  "comment": "Great stay!",
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "user@example.com",
    "password": "password123"
  },
  "hotel": {
    "id": 1,
    "name": "Hotel Name",
    "latitude": 40.7128,
    "longitude": -74.0060
  }
}
```

Response:

```json
{
  "id": 1,
  "rating": 5,
  "comment": "Great stay!",
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "user@example.com",
    "password": "password123"
  },
  "hotel": {
    "id": 1,
    "name": "Hotel Name",
    "latitude": 40.7128,
    "longitude": -74.0060
  }
}
```

### Get Reviews for a Hotel

URL: /getReviews/{hotelId}

Method: GET

Response:

```json
[
  {
    "id": 1,
    "rating": 5,
    "comment": "Great stay!",
    "user": {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "user@example.com",
      "password": "password123"
    },
    "hotel": {
      "id": 1,
      "name": "Hotel Name",
      "latitude": 40.7128,
      "longitude": -74.0060
    }
  }
]
```
### Get Hotels Within Radius

URL: /withinRadius

Method: GET

Query Parameter: radius (float)

Response:

```json
[
  {
    "id": 1,
    "name": "Hotel Name",
    "latitude": 40.7128,
    "longitude": -74.0060,
    "rooms": [
      {
        "id": 101,
        "roomNumber": 101,
        "type": 1,
        "price": 100.0,
        "isAvailable": true
      }
    ],
    "reviews": [
      {
        "id": 1,
        "rating": 5,
        "comment": "Great stay!",
        "user": {
          "id": 1,
          "firstName": "John",
          "lastName": "Doe",
          "email": "user@example.com",
          "password": "password123"
        }
      }
    ]
  }
]
```


### Add a Booking

URL: /bookRoom

Method: POST

Request Body:

```json
{
  "id": 1,
  "checkInDate": "2024-05-18T14:00:00",
  "checkOutDate": "2024-05-20T11:00:00",
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "user@example.com",
    "password": "password123"
  },
  "room": {
    "id": 101,
    "roomNumber": 101,
    "type": 1,
    "price": 100.0,
    "isAvailable": true,
    "hotel": {
      "id": 1,
      "name": "Hotel Name",
      "latitude": 40.7128,
      "longitude": -74.0060
    }
  }
}
```

Response:

```json
{
  "id": 1,
  "checkInDate": "2024-05-18T14:00:00",
  "checkOutDate": "2024-05-20T11:00:00",
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "user@example.com",
    "password": "password123"
  },
  "room": {
    "id": 101,
    "roomNumber": 101,
    "type": 1,
    "price": 100.0,
    "isAvailable": true,
    "hotel": {
      "id": 1,
      "name": "Hotel Name",
      "latitude": 40.7128,
      "longitude": -74.0060
    }
  }
}
```

### Get Bookings for a User

URL: /getBookings/{userId}

Method: GET

Response:

```json
[
  {
    "id": 1,
    "checkInDate": "2024-05-18T14:00:00",
    "checkOutDate": "2024-05-20T11:00:00",
    "user": {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "user@example.com",
      "password": "password123"
    },
    "room": {
      "id": 101,
      "roomNumber": 101,
      "type": 1,
      "price": 100.0,
      "isAvailable": true,
      "hotel": {
        "id": 1,
        "name": "Hotel Name",
        "latitude": 40.7128,
        "longitude": -74.0060
      }
    }
  }
]
```

### UserController

Register a User

URL: /usersRegister

Method: POST

Request Body:

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "user@example.com",
  "password": "password123"
}
```

Response:
```json
{
  "message": "Auth successful"
}
```

### Authenticate a User

URL: /usersAuth

Method: POST

Request Body:

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Response:

*Success:*

```json
{
  "message": "Authentication successful"
}
```
*Failure:*

```json
{
  "message": "Invalid username or password"
}
```







## Installation
### Prerequisites
Java Development Kit (JDK) 11 or higher

Maven 3.6 or higher

A running instance of a database (preferably MySQL)

## Steps
**Clone the repository:**

```bash
git clone <repository-url>
cd repository-directory
```

**Update the application.properties file with your database configuration:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

**Build the project:**

```bash
mvn clean install
```
**Run the application:**

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8080.

## Usage
**Running the Application**

After following the installation steps, you can run the application using:

```bash
mvn spring-boot:run
```
