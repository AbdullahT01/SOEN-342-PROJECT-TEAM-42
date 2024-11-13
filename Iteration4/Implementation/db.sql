-- Table for storing clients
CREATE TABLE Client (
    ClientID SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Age INT NOT NULL,
    HasGuardian BOOLEAN NOT NULL
);

-- Table for storing instructors
CREATE TABLE Instructor (
    InstructorID SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Specialization VARCHAR(50) NOT NULL
);

-- Table for storing locations
CREATE TABLE Location (
    LocationID SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    FacilityType VARCHAR(50) NOT NULL
);

-- Table for storing schedules
CREATE TABLE Schedule (
    ScheduleID SERIAL PRIMARY KEY,
    Day VARCHAR(10) NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL
);

-- Table for storing offerings
CREATE TABLE Offering (
    OfferingID SERIAL PRIMARY KEY,
    Type VARCHAR(50) NOT NULL,
    Mode VARCHAR(10) NOT NULL,
    Available BOOLEAN DEFAULT TRUE,
    LocationID INT,
    ScheduleID INT,
    InstructorID INT,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID),
    FOREIGN KEY (ScheduleID) REFERENCES Schedule(ScheduleID),
    FOREIGN KEY (InstructorID) REFERENCES Instructor(InstructorID)
);

-- Table for storing bookings
CREATE TABLE Booking (
    BookingID SERIAL PRIMARY KEY,
    ClientID INT NOT NULL,
    OfferingID INT NOT NULL,
    Status VARCHAR(20) NOT NULL,
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
    FOREIGN KEY (OfferingID) REFERENCES Offering(OfferingID)
);
