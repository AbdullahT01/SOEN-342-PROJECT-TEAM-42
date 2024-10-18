Mostafa Mohamed 40201893

Abdullah 

Urgent for TA: Sorry for adding you late because we added wrong TA.
___________________________________________________________________________________________________________________________________________________________

# Project Iteration: Use Case 1 - Process Offerings

## Overview

This iteration focuses on Use Case 1: Process Offerings, involving two main actors: Administrator and Instructor. The Administrator creates and makes offerings (e.g., classes) available, while Instructors can select these offerings to teach. Public Users can view available offerings but cannot book or manage them.

- Actors:

Administrator: Creates and manages offerings.

Instructor: Views and selects offerings to teach.

Public User: Views offerings.

- Deliverables

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

