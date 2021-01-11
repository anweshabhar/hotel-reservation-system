DROP TABLE IF EXISTS Hotel_Info;

CREATE TABLE Hotel_Info (
  hotel_Id int(18) PRIMARY KEY,
  hotel_Name varchar(25) NOT NULL,
  hotel_Desc varchar(100),
  city varchar(25)
);

DROP TABLE IF EXISTS Rooms;

CREATE TABLE Rooms (
  room_Id int(18) PRIMARY KEY,
  hotel_Id int(18),
  room_Type varchar(25) NOT NULL,
  price int(18),
  CONSTRAINT rooms_fk_1 FOREIGN KEY (hotel_Id) REFERENCES Hotel_Info (hotel_Id)
) ;
