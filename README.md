Project Iteration: Use Case 1 - Process Offerings
Overview
This iteration focuses on Use Case 1: Process Offerings, involving two main actors: Administrator and Instructor. The Administrator creates and makes offerings (e.g., classes) available, while Instructors can select these offerings to teach. Public Users can view available offerings but cannot book or manage them.

Actors:
Administrator: Creates and manages offerings.
Instructor: Views and selects offerings to teach.
Public User: Views offerings.
Deliverables
1. UML Domain Model [+ Package Diagram]
Represents the structure of the system, including classes like Instructor, Offering, Location, Schedule, and Administrator.
Organized into three packages: User Package, Offering Package, and Admin Package.
2. System Sequence Diagrams
Success Scenario: Shows the process of offering creation by the Administrator.
Instructor Selection: Illustrates how an Instructor selects an offering, including both successful and failed attempts.
3. System Operations and Contracts
createOffering(): Allows an Administrator to add a new offering if the schedule is free.
selectOffering(): Lets an Instructor select an available offering.
Includes preconditions and postconditions for each operation.
4. UML Interaction Diagrams
Diagrams show interactions between actors and the system during offering creation and selection.
5. UML Class Diagram
Shows the structure of the system's classes and their relationships.
Includes classes like Offering, Location, Schedule, Instructor, and Administrator.
6. Implementation
Java classes are organized under the offeringsystem package:
Offering, Instructor, Location, Schedule, Administrator, and Main.
Main.java demonstrates the process of creating and selecting offerings.
Repository Structure
css
Copy code
src/
└── iteration one/
    ├── Administrator.java
    ├── Instructor.java
    ├── Location.java
    ├── Schedule.java
    ├── Offering.java
    └── Main.java
How to Run
Clone the repository and open it in an IDE like IntelliJ.
Run Main.java to see how the Administrator creates an offering and how an Instructor selects it.
Output will indicate whether the selection was successful.
Summary
This iteration delivers the design and implementation of the Process Offerings use case, providing a clear flow for creating and selecting offerings. The project includes well-structured diagrams and a Java implementation, aligning with the requirements.

