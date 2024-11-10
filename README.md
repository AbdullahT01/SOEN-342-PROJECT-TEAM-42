Mostafa Mohamed 40201893

Abdullah Taha 40261146

Urgent for TA: Sorry for adding you late because we added wrong TA.
___________________________________________________________________________________________________________________________________________________________

# Project Iteration: Use Case 1 - Process Offerings

## Overview

This iteration focuses on Use Case 1: Process Offerings, involving two main actors: Administrator and Instructor. The Administrator creates and makes offerings (e.g., classes) available, while Instructors can select these offerings to teach. Public Users can view available offerings but cannot book or manage them.

- Actors:

Administrator: Creates and manages offerings.

Instructor: Views and selects offerings to teach.

Public User: Views offerings.

- Deliverables (Implementations --> iteration one || Diagrams --> UML Diagrams)

UML Domain Model [+ Package Diagram]
Represents the structure of the system, including classes like Instructor, Offering, Location, Schedule, and Administrator.
Organized into three packages: User Package, Offering Package, and Admin Package.

System Sequence Diagrams
Success Scenario: Shows the process of offering creation by the Administrator.
Instructor Selection: Illustrates how an Instructor selects an offering, including both successful and failed attempts.

System Operations and Contracts
createOffering(): Allows an Administrator to add a new offering if the schedule is free.
selectOffering(): Lets an Instructor select an available offering.
Includes preconditions and postconditions for each operation.

UML Interaction Diagrams
Diagrams show interactions between actors and the system during offering creation and selection.

UML Class Diagram
Shows the structure of the system's classes and their relationships.
Includes classes like Offering, Location, Schedule, Instructor, and Administrator.

## Implementation
The Java implementation includes the classes defined in the UML diagrams and follows the operation contracts:

Main.java: Demonstrates the creation of an offering by the Administrator and the selection of the offering by an Instructor.


Offering.java: Contains methods for assigning instructors and checking availability.

Instructor.java: Allows instructors to select offerings.

Administrator.java: Manages the creation of new offerings.

Location.java and Schedule.java: Support offering details like time slots and venues.


## How to Run

Clone the repository and open it in an IDE like IntelliJ.
Run Main.java to see how the Administrator creates an offering and how an Instructor selects it.
Output will indicate whether the selection was successful.

## Summary

This iteration delivers the design and implementation of the Process Offerings use case, providing a clear flow for creating and selecting offerings. The project includes well-structured diagrams and a Java implementation, aligning with the requirements.

----------------------------------------------------------------------------------------------------

# Iteration Four 

### - Rational Data Model 

![Relational data model](https://github.com/user-attachments/assets/285ca1c8-5055-4ed9-a927-2ade56627da2)


This relational data model illustrates the structure of our lesson booking system. Each entity represents a key component, including _Location_, _Lesson_, _Schedule_, _Client_, _Booking_, _Instructor_, and _InstructorAvailability_. Relationships between entities are defined to ensure data integrity and streamline the booking process.

- **Location** holds details about where lessons are conducted.
- **Lesson** captures information about different types of lessons, their modes, and durations.
- **Schedule** links specific lessons to locations and times.
- **Client** contains client information, including age and guardian details when needed.
- **Booking** records each client’s lesson booking, referencing both client and schedule.
- **Instructor** provides instructor data, while InstructorAvailability specifies the locations where each instructor is available.

This model ensures normalized data storage, effective querying, and adherence to business rules such as availability constraints and age-based requirements for guardian presence.


### - Domain Model 

![Domain Model Diagram](https://github.com/user-attachments/assets/bba3824e-3373-4454-83d2-c663a1c9ad97)

This domain model diagram represents the main parts of our lesson booking system and how they relate to each other:

- **Location** represents where lessons take place.
- **Lesson** contains the type, mode, and duration of each lesson.
- **Schedule** links lessons with locations and specific times.
- **Client** includes basic information about clients, like age and guardian info if needed.
- **Booking** records which client has signed up for which schedule.
- **Instructor** provides details about each instructor.
- **InstructorAvailability** shows where each instructor is available.

The relationships in this model help ensure that clients can book lessons, instructors are assigned to locations, and schedules are organized effectively.


### - Interaction Diagram 

![Interaction Diagram](https://github.com/user-attachments/assets/a9ea0e56-3804-4a15-acb4-ef7025dfdf07)



This interaction diagram shows the steps involved when a client makes a booking in the system:

1. **Client Request:** The client initiates a booking by sending their ID, the lesson offering, date, and time slot.
2. **Age Check:** The system checks the client’s age to ensure they’re eligible. If the client is under 18, the system verifies that they have a guardian.
3. **Guardian Check:** If the client is underage, the system confirms if a guardian exists for the client.
4. **Booking Uniqueness:** The system checks if there is already a booking for the same date and time slot to avoid duplicate bookings.
5. **Store Booking:** Once all conditions are met, the system stores the booking details in the database.
6. **Confirmation:** The client receives a confirmation that their booking has been successfully processed.

This process ensures that bookings are only made if all requirements are satisfied, including guardian verification for underage clients and avoiding duplicate bookings.



