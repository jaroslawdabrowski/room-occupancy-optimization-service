package com.jarek.roomoccupancyopt.repository.jpa;

import com.jarek.roomoccupancyopt.repository.jpa.model.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGuestRepository extends JpaRepository<GuestEntity, String> {
}
