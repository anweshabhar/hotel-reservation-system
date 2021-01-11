INSERT INTO Hotel_Info (hotel_Id, hotel_Name, hotel_Desc, city) VALUES
  (1, 'Novotel', '5 star hotel with great facilities', 'Kolkata'),
  (2, 'Ibis', '4 star hotel', 'Mumbai'),
  (3, 'Radisson', '4 star hotel' ,'Hyderabad');

INSERT INTO Rooms (room_Id,hotel_Id, room_Type, price) VALUES
  (1, 1, 'KingBed', 2000),
  (2, 1, 'DoubleQueen', 3000),
  (3, 1, 'RunOfTheHouse', 1000),
  (4, 2, 'KingBed',2000),
  (5, 2, 'DoubleQueen',3000),
  (6, 2, 'RunOfTheHouse',1000),
  (7, 3, 'KingBed', 2000),
  (8, 3, 'DoubleQueen',3000),
  (9, 3, 'RunOfTheHouse',1000);