INSERT INTO Hotel_Info (hotel_Id, hotel_Name, hotel_Desc, city) VALUES
  (1, 'Novotel', '5 star hotel with great facilities', 'Kolkata'),
  (2, 'Ibis', '4 star hotel', 'Mumbai'),
  (3, 'Radisson', '4 star hotel' ,'Hyderabad');

INSERT INTO Rooms (room_Id,hotel_Id, room_Type, desc) VALUES
  (1, 1, 'KingBed', 'king bed'),
  (2, 1, 'DoubleQueen', 'double queen bed'),
  (3, 1, 'RunOfTheHouse', 'run of the house'),
  (4, 2, 'KingBed','king bed'),
  (5, 2, 'DoubleQueen','double queen bed'),
  (6, 2, 'RunOfTheHouse','run of the house'),
  (7, 3, 'KingBed', 'king bed'),
  (8, 3, 'DoubleQueen','double queen bed'),
  (9, 3, 'RunOfTheHouse','run of the house');