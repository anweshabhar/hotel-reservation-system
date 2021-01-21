package com.example.reservationservice.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.reservationservice.entity.Reservation;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ReservationRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ReservationRepository reservationRepository;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Test
	void testFindReservation() {
		Reservation reservation = new Reservation();
		String checkIn = "16-01-2021";
		String checkOut = "17-01-2021";
		try {
			reservation.setCheckIn(sdf.parse(checkIn));
			reservation.setCheckOut(sdf.parse(checkOut));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reservation.setCity("Mumbai");
		reservation.setHotelName("Taj");
		Reservation resp = entityManager.persist(reservation);
		List<Reservation> lst = reservationRepository.findReservation(reservation.getCheckIn(), reservation.getCheckOut());
		assertNotNull(lst);
		assertEquals(resp.getId(), lst.get(0).getId());
	}

}
