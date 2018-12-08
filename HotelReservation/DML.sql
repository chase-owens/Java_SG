USE HotelReservation;

SELECT DollarDiscount
FROM Promo
WHERE  PromoCode = '57-juju';

UPDATE Promo
SET DollarDiscount = 50
WHERE PromoCode = '57-juju';

SELECT DollarDiscount
FROM Promo
WHERE  PromoCode = '57-juju';


UPDATE Reservation
SET PromoCode = NULL
WHERE  PromoCode = '57-jk';

DELETE FROM Promo
WHERE PromoCode = '57-jk';

SELECT CustomerName, GuestName
FROM Reservation
INNER JOIN Customer ON Customer.CustomerId = Reservation.CustomerId
INNER JOIN GuestsExpectedInRoom ON GuestsExpectedInRoom.ReservationId = Reservation.ReservationId
INNER JOIN Guest ON Guest.GuestId = GuestsExpectedInRoom.GuestId
WHERE Reservation.ReservationId = 1;

SELECT Room.RoomNumber
FROM Room
LEFT JOIN RoomReservation ON RoomReservation.RoomNumber = Room.RoomNumber
LEFT JOIN Reservation ON Reservation.ReservationId = RoomReservation.ReservationId
WHERE ('2019-01-01'  NOT BETWEEN Reservation.ArrivalDate AND Reservation.DepartureDate) OR (Reservation.ArrivalDate IS NULL AND Reservation.DepartureDate IS NULL);