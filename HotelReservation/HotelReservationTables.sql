DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE IF NOT EXISTS HotelReservation;

USE HotelReservation;

CREATE TABLE DateRange(
	DateRangeId INT AUTO_INCREMENT PRIMARY KEY,
    StartDate DATE NOT NULL,
    LastDate DATE NOT NULL
);

CREATE TABLE Customer(
	CustomerId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName VARCHAR(50) NOT NULL,
    CustomerPhone VARCHAR(13) NOT NULL,
    Email VARCHAR(50) NOT NULL
);

CREATE TABLE Guest(
	GuestId INT AUTO_INCREMENT PRIMARY KEY,
    GuestName VARCHAR(50) NOT NULL,
    GuestDOB DATE NOT NULL
);

CREATE TABLE Promo(
	PromoCode VARCHAR(35) NOT NULL PRIMARY KEY,
    PromoName VARCHAR(50) NOT NULL,
    PercentDiscount DECIMAL(5, 2) NULL,
    DollarDiscount DECIMAL(9,2) NULL,
    DateRangeId INT NOT NULL
);

CREATE TABLE RoomType(
	RoomTypeId INT AUTO_INCREMENT PRIMARY KEY,
    TypeName VARCHAR(50)
);

CREATE TABLE Amenity(
	AmenityId  INT PRIMARY KEY,
    AmenityType VARCHAR(30)
);

CREATE TABLE AddOn(
	AddOnId  INT PRIMARY KEY,
    AddOnType VARCHAR(30),
    AddOnPrice DECIMAL(9,2)
);

CREATE TABLE Room(
	RoomNumber INT PRIMARY KEY,
    FloorNumber INT NOT NULL,
    RoomTypeId INT NOT NULL,
    BaseRate DECIMAL(9,2) NOT NULL,
    
    FOREIGN KEY fk_Room_RoomType(RoomTypeId)
		REFERENCES RoomType(RoomTypeId)
);

CREATE TABLE RoomAmenities(
	RoomNumber INT NOT NULL,
    AmenityId  INT NOT NULL,
    
    PRIMARY KEY(RoomNumber, AmenityId),
    FOREIGN KEY fk_RoomAmenities_RoomNumber(RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomAmenities_AmenityId(AmenityId)
		REFERENCES Amenity(AmenityId)
);

CREATE TABLE Reservation(
	ReservationId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerId INT NOT NULL,
    ArrivalDate DATE NOT NULL,
    DepartureDate DATE NOT NULL,
    PromoCode VARCHAR(35) NULL,
    
    FOREIGN KEY fk_Reservation_CustomerId(CustomerId)
		REFERENCES Customer(CustomerId),
	FOREIGN KEY fk_Reservation_PromoCode(PromoCode)
		REFERENCES Promo(PromoCode)
);

CREATE TABLE RoomReservation(
	ReservationId INT NOT NULL,
    RoomNumber INT NOT NULL,
    
    
    PRIMARY KEY(ReservationId, RoomNumber),
    FOREIGN KEY fk_RoomReservation_RoomNumber(RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomReservation_ReservationId(ReservationId)
		REFERENCES Reservation(ReservationId)
);

CREATE TABLE RoomRate(
	RoomTypeId INT NOT NULL,
    DateRangeId INT NOT NULL,
    UpdatePrice DECIMAL(9, 2) NOT NULL
);

CREATE TABLE GuestsExpectedInRoom(
	ReservationId INT NOT NULL,
    GuestId INT NOT NULL,
    
    PRIMARY KEY (ReservationId, GuestId),
    FOREIGN KEY fk_GuestsExpectedInRoom_ReservationId(ReservationId)
		REFERENCES Reservation(ReservationId),
	FOREIGN KEY fk_GuestsExpectedInRoom_GuestId(GuestId)
		REFERENCES Guest(GuestId)
);

CREATE TABLE Bill(
	BillId INT AUTO_INCREMENT PRIMARY KEY,
    SubTotal DECIMAL(9,2) NOT NULL, 
    Tax DECIMAL(9,2) NOT NULL, 
    Total DECIMAL(9,2) NOT NULL,
    ReservationId INT NOT NULL,
    
    FOREIGN KEY fk_BillDetails_ReservationId(ReservationId)
		REFERENCES Reservation(ReservationId)
);

CREATE TABLE AdditionsToBill(
	AdditionsId INT AUTO_INCREMENT PRIMARY KEY,
    AddOnId INT NOT NULL,
    BillId INT NOT NULL,
    
    FOREIGN KEY fk_AdditionsToBill_AddOnId(AddOnId)
		REFERENCES AddOn(AddOnId),
	FOREIGN KEY fk_AdditionsToBill_BillId(BillId)
		REFERENCES Bill(BillId)
);

CREATE TABLE BillDetails(
	BillDetailsId INT AUTO_INCREMENT PRIMARY KEY,
    BillId INT NOT NULL,
    DateProcessed DATE NULL,
    isPaid TINYINT DEFAULT 0,
    
	FOREIGN KEY fk_Bill_BillId(BillId)
		REFERENCES Bill(BillId)
);