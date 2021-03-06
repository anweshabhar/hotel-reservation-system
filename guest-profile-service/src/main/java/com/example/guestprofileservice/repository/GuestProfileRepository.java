package com.example.guestprofileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.guestprofileservice.entity.GuestProfile;

@Repository
public interface GuestProfileRepository extends JpaRepository<GuestProfile, Long>{

	List<GuestProfile> findByCreatedBy(String user);
}
