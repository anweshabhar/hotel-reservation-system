INSERT INTO Hotel_Info (hotel_Id, hotel_Name, hotel_Desc, city) VALUES
  (1, 'Novotel', '5 star hotel with great facilities', 'Kolkata'),
  (2, 'Ibis', '4 star hotel', 'Mumbai'),
  (3, 'Radisson', '4 star hotel' ,'Hyderabad'),
  (4, 'Taj', '5 star hotel', 'Mumbai');

INSERT INTO Rooms (room_Id,hotel_Id, room_Type, price, max_guest_allowed ) VALUES
  (1, 1, 'KingBed', 2000, 3),
  (2, 1, 'DoubleQueen', 3000, 4),
  (3, 1, 'RunOfTheHouse', 1000, 2),
  (4, 2, 'KingBed',2000, 3),
  (5, 2, 'DoubleQueen',3000, 4),
  (6, 2, 'RunOfTheHouse',1000, 2),
  (7, 3, 'KingBed', 2000, 3),
  (8, 3, 'DoubleQueen',3000, 4),
  (9, 3, 'RunOfTheHouse',1000, 2),
  (10, 1, 'KingBed', 2000, 3),
  (11, 1, 'DoubleQueen', 3000, 4),
  (12, 1, 'RunOfTheHouse', 1000, 2);