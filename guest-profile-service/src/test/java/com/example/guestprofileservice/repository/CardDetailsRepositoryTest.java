package com.example.guestprofileservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.guestprofileservice.entity.CardDetails;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CardDetailsRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CardDetailsRepository repo;

	@Test
	void testFindByCreatedBy() {
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardNo("123456789");
		cardDetails.setCreatedBy("abc");
		cardDetails.setCreatedOn(new Date());
		cardDetails.setExpiryMonth("02");
		cardDetails.setExpiryYr("2030");
		cardDetails.setName("test user");
		CardDetails savedCard = entityManager.persist(cardDetails);
		Optional<CardDetails> resp = repo.findByCreatedBy("abc");
		assertEquals(savedCard.getId(), resp.get().getId());
	}

}
