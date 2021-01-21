package com.example.guestprofileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestprofileservice.entity.StayHistory;

public interface StayHistoryRepository extends JpaRepository<StayHistory, Integer>{

}
