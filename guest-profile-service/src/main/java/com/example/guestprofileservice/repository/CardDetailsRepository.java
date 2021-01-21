package com.example.guestprofileservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestprofileservice.entity.CardDetails;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Integer>{

	Optional<CardDetails> findByCreatedBy(String user);
}
