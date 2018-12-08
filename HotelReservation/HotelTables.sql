USE HotelReservation;

INSERT INTO DateRange (StartDate, LastDate) values 
	('2018-12-20', '2019-01-05'),
    ('2019-2-01', '2019-05-31'),
    ('2019-12-20', '2020-01-05');

INSERT INTO Customer (CustomerName, CustomerPhone, Email) values 
	('Sean', '214-916-0963', 'sean@domain.com'),
    ('Ricky', '214-916-0963', 'ricky@domain.com'),
	('Chase', '214-916-0963', 'chase@domain.com');

INSERT INTO Guest (GuestName, GuestDOB) values 
	('Chase', '1983-10-05'),
    ('Jenny', '1983-12-07'),
    ('Ricky', '1983-10-12'),
    ('Bryson', '1995-12-01'),
    ('Sean', '1986-10-01');

INSERT INTO Promo (PromoCode, PromoName, PercentDiscount, DateRangeId) values ('57-jk', 'NewYear', 15, 2);

INSERT INTO Promo (PromoCode, PromoName, DollarDiscount, DateRangeId) values ('57-juju', "We're Sorry", 20, 2);

INSERT INTO RoomType (RoomTypeId, TypeName) values 
	(1, 'single-twin'),
    (2, 'double-twin'),
    (3, 'single-queen'),
    (4, 'double-twin'),
    (5, 'king'),
    (6, 'penthouse-suite');

INSERT INTO Amenity (AmenityId, AmenityType) values 
	(1, 'microwave'),
    (2, 'wi-fi'),
    (3, 'cable'),
    (4, 'hot tub');

INSERT INTO AddOn (AddOnId, AddOnType, AddOnPrice) values 
	(1, 'roomService', 30),
    (2, 'wake up call', 5),
    (3, 'dry cleaing', 40),
    (4, 'massage', 50);

INSERT INTO Room (RoomNumber, FloorNumber, RoomTypeId, BaseRate) values 
	(101, 1, 1, 49.99),
    (102, 1, 1, 49.99),
    (103, 1, 1, 49.99),
    (104, 1, 1, 49.99),
    (105, 1, 1, 49.99),
    (106, 1, 1, 49.99),
    (107, 1, 1, 49.99),
    (108, 1, 1, 49.99),
    (109, 1, 1, 49.99),
    (110, 1, 1, 49.99),
    (111, 1, 2, 59.99),
    (112, 1, 2, 59.99),
    (113, 1, 2, 59.99),
    (114, 1, 2, 59.99),
    (115, 1, 2, 59.99);
    
INSERT INTO Room (RoomNumber, FloorNumber, RoomTypeId, BaseRate) values 
	(201, 2, 2, 59.99),
    (202, 2, 2, 59.99),
    (203, 2, 2, 59.99),
    (204, 2, 2, 59.99),
    (205, 2, 2, 59.99),
    (206, 2, 2, 59.99),
    (207, 2, 2, 59.99),
    (208, 2, 2, 59.99),
    (209, 2, 2, 59.99),
    (210, 2, 2, 59.99),
    (211, 2, 3, 89.99),
    (212, 2, 3, 89.99),
    (213, 2, 3, 89.99),
    (214, 2, 3, 89.99),
    (215, 2, 3, 89.99);
    
INSERT INTO Room (RoomNumber, FloorNumber, RoomTypeId, BaseRate) values 
	(301, 3, 3, 59.99),
    (302, 3, 3, 59.99),
    (303, 3, 3, 59.99),
    (304, 3, 3, 59.99),
    (305, 3, 3, 59.99),
    (306, 3, 3, 59.99),
    (307, 3, 3, 59.99),
    (308, 3, 3, 59.99),
    (309, 3, 3, 59.99),
    (310, 3, 3, 59.99),
    (311, 3, 4, 89.99),
    (312, 3, 4, 89.99),
    (313, 3, 4, 89.99),
    (314, 3, 4, 89.99),
    (315, 3, 4, 89.99);
    
INSERT INTO Room (RoomNumber, FloorNumber, RoomTypeId, BaseRate) values 
	(401, 4, 4, 89.99),
    (402, 4, 4, 89.99),
    (403, 4, 4, 89.99),
    (404, 4, 4, 89.99),
    (405, 4, 4, 89.99),
    (406, 4, 4, 89.99),
    (407, 4, 4, 89.99),
    (408, 4, 4, 89.99),
    (409, 4, 4, 89.99),
    (410, 4, 4, 89.99),
    (411, 4, 5, 119.99),
    (412, 4, 5, 119.99),
    (413, 4, 5, 119.99),
    (414, 4, 5, 119.99),
    (415, 4, 5, 119.99);
    
INSERT INTO Room (RoomNumber, FloorNumber, RoomTypeId, BaseRate) values 
	(501, 5, 6, 349.99),
    (502, 5, 6, 349.99);
    
INSERT INTO RoomRate(RoomTypeId, DateRangeId, UpdatePrice) values
	(1, 1, 20),
    (2, 1, 40),
    (3, 1, 50),
    (4, 1, 60),
    (5, 1, 100),
    (6, 1, 250),
    (1, 3, 20),
    (2, 3, 40),
    (3, 3, 50),
    (4, 3, 60),
    (5, 3, 100),
    (6, 3, 250);

INSERT INTO RoomAmenities (RoomNumber, AmenityId) values 
	(101, 1),
    (102, 1),
    (103, 1),
    (104, 1),
    (105, 1),
    (106, 1),
    (107, 1),
    (108, 1),
    (109, 1),
    (110, 1),
    (111, 1),
    (112, 1),
    (113, 1),
    (114, 1),
    (115, 1),
	(201, 1),
    (202, 1),
    (203, 1),
    (204, 1),
    (205, 1),
    (206, 1),
    (207, 1),
    (208, 1),
    (209, 1),
    (210, 1),
    (211, 1),
    (212, 1),
    (213, 1),
    (214, 1),
    (215, 1),
	(201, 2),
    (202, 2),
    (203, 2),
    (204, 2),
    (205, 2),
    (206, 2),
    (207, 2),
    (208, 2),
    (209, 2),
    (210, 2),
    (211, 2),
    (212, 2),
    (213, 2),
    (214, 2),
    (215, 2),
    (501, 1),
    (501, 2),
    (501, 3),
    (501, 4),
	(502, 1),
    (502, 2),
    (502, 3),
    (502, 4);

INSERT INTO Reservation (ReservationId, CustomerId, ArrivalDate, DepartureDate) values 
	(1, 3, '2019-03-12', '2019-03-15'),
    (2, 2, '2019-08-12', '2019-08-16');

INSERT INTO Reservation (ReservationId, CustomerId, ArrivalDate, DepartureDate, PromoCode) values 
	(3, 1, '2019-12-25', '2019-12-31', '57-jk');

INSERT INTO RoomReservation (ReservationId, RoomNumber) values 
	(3, 102),
    (2, 211),
    (2, 212),
    (1, 502),
    (1, 501);

INSERT INTO GuestsExpectedInRoom (ReservationId, GuestId) values 
	(3, 5),
    (2, 3),
    (1, 1),
    (1, 2),
    (1, 4);

INSERT INTO Bill (SubTotal, Tax, Total, ReservationId) values 
	(700, 100, 800, 1),
    (180, 15, 195, 2),
    (50, 4, 54, 3);

INSERT INTO AdditionsToBill (AddOnId, BillId) values 
	(1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (4, 1),
    (4, 1),
    (1, 2);

INSERT INTO BillDetails (BillId) values (1);

INSERT INTO BillDetails (BillId, DateProcessed, isPaid) values
	(2, '2018-11-01', 1),
    (3, '2018-11-01', 1);