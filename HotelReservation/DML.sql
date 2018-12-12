USE HotelReservation;

-- Change promo

-- Initial amount
SELECT DollarDiscount
FROM Promo
WHERE  PromoCode = '57-juju';
-- Update discount
UPDATE Promo
SET DollarDiscount = 50
WHERE PromoCode = '57-juju';
-- Subsequent Amount
SELECT DollarDiscount
FROM Promo
WHERE  PromoCode = '57-juju';


-- Remove promo 
-- Set promocode to null where promo to be removed exists
UPDATE Reservation
SET PromoCode = NULL
WHERE  PromoCode = '57-jk';
-- Remove promo after tables with promo have been set to null (or deleted)
DELETE FROM Promo
WHERE PromoCode = '57-jk';

-- Get customer and guest name for reservation
SELECT CustomerName, GuestName
FROM Reservation
INNER JOIN Customer ON Customer.CustomerId = Reservation.CustomerId
INNER JOIN GuestsExpectedInRoom ON GuestsExpectedInRoom.ReservationId = Reservation.ReservationId
INNER JOIN Guest ON Guest.GuestId = GuestsExpectedInRoom.GuestId
WHERE Reservation.ReservationId = 1;

--
SELECT Room.RoomNumber
FROM Room
LEFT JOIN RoomReservation ON RoomReservation.RoomNumber = Room.RoomNumber
LEFT JOIN Reservation ON Reservation.ReservationId = RoomReservation.ReservationId
WHERE ('2019-01-01'  NOT BETWEEN Reservation.ArrivalDate AND Reservation.DepartureDate) OR (Reservation.ArrivalDate IS NULL AND Reservation.DepartureDate IS NULL);


SELECT RoomNumber
FROM RoomReservation
INNER JOIN Reservation ON Reservation.ReservationId = RoomReservation.ReservationId
WHERE RoomReservation.ReservationId = 1;

SELECT CustomerName, COUNT(RoomNumber)
FROM RoomReservation
INNER JOIN Reservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN Customer ON Customer.CustomerId = Reservation.CustomerId
GROUP BY Customer.CustomerId;