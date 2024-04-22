# usMobileCoding

# Service Implementation: Cycle Service
## Methods
1. ### getCurrentCycleDailyUsage(CycleRequest request):
- This method retrieves the current cycle's daily usage for a given user based on the provided CycleRequest object.
- It fetches the current cycle from the database using CycleRepository findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual method.
- Retrieves daily usage data for the current cycle using DailyUsageRepository findDailyUsageByUserIdAndMdnAndUsageDateBetween method.
- Constructs a CurrentCycleDailyUsageResponse object containing the daily usage data for the current cycle and returns it.

2. ### getCycleHistoryByMdn(CycleRequest request):
- Retrieves the cycle history based on mobile number (mdn) and user ID specified in the CycleRequest object.
- Fetches the cycle history from the database using CycleRepository findCycleHistoryByUserIdAndMdn method.
- Constructs a CycleHistoryResponse object containing the cycle history data and returns it.

### I used the *Naming Query* in the CycleRepository and projection on the required fields Only for optimisation and performance gains.



#  Service Implementation: User Service

## Methods
### createUser(UserRequest userRequest):

This method is responsible for creating a new user based on the provided UserRequest object.
It first converts the UserRequest object into a User entity using the userRequestToUser method. 
**we use the request and response objects to not expose our document structure to the controller**
- It Checks if a user with the same email or mobile number already exists in the database :
If a user with the same email or mobile number exists, it throws an EntityAlreadyExists exception.
Otherwise, it inserts the new user into the database using UserRepository insert method.
Returns a UserResponse object representing the created user.

### updateUser(UserRequest userRequest):
This method updates an existing user's profile based on the provided UserRequest object. 
- It converts the UserRequest object into a User entity using the userRequestToUser method.
- Updates the user's information in the database using the UserRepository save method.
- Returns a UserResponse object representing the updated user.

### transferMdn(String sourceUserId, String targetUserId):
Transfers the mobile number (mdn) from one user to another.
- Fetches the source user and target user from the database using their IDs.
- Sets the mdn of the source user to null to avoid duplication.
- Saves the updated source user and target user in the database using UserRepository save method.
## Dependencies

- ### UserRepository: Used for database operations related to users (e.g., finding users by ID, email, or mobile number, inserting or updating users).
## Error Handling
- ### EntityAlreadyExists: Thrown when attempting to create a user with an existing email or mobile number.


# Repository Implementation

## CycleRepository
 I used the *Naming Query* in the CycleRepository and the projection on the required fields Only for query optimisation and performance gains.

## DailyUsageRepository
 I named the object returned by the DailyUsageDTO just to specify that I am not returning the whole document with all the fields but specific fields only for optimisation 
 
# Additional Requirements:

## Unit Tests with mock data
I add some unit tests to cover most the cases
## Data tests with an embedded MongoDB server
I added some Data tests for CycleRepository using an embedded Mongo Server **flapdoodle** 
## Add initial data inside your MongoDB database
I added some code to insert some Data in the DB for testing 

# APIs 
## Cycle Current Daily Usage
This endpoint is a POST request to :
**`localhost:8080/api/v1/cycle/current-cycle-daily-usage`**, which is used to retrieve the daily usage for the current cycle.
### Request Parameters
- **userId** (string, required): The unique identifier of the user.
- **mdn** (string, required): The mobile device number.

### Response
The response will contain an array of dailyUsageDTOS, each representing the daily usage for the current cycle.

**usageDate** (string): The date of the usage.
**usedInMb** (number): The amount of data used in megabytes for the specific date.

## Cycle History
This endpoint is a POST request to :
`localhost:8080/api/v1/cycle/cycle-history?userId=66261618f213ef209da95334&mdn=12347562340`,This API endpoint allows you to retrieve the cycle history for a specific user and mobile device number.

### Request Parameters
**userId:** The unique identifier of the user.

**mdn:** The mobile device number.

### Response
Upon a successful request, the API returns a JSON object with a cycleHistoryDTOList array containing cycle history objects. Each cycle history object includes the following properties:

**id:** The unique identifier of the cycle history.

**startDate:** The start date of the cycle.

**endDate:** The end date of the cycle.

# Bonus functionality you can choose to implement:
## 1. Use of docker container
## 2. Improvements
- Use a distributed NoSQL DataBase like Cassandra and shard the Data by region horizontal sharding
- Adding pagination would give a better user experience
