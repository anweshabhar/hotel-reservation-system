package com.example.reservationservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.reservationservice.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select r from Reservation r where (r.checkIn<=?1 and r.checkOut>=?1) or (r.checkIn<?2 and r.checkOut>=?2) or"
			+ " (r.checkIn>=?1 and r.checkIn<=?2)")
	List<Reservation> findReservation(Date checkIn,Date checkOut);
}
