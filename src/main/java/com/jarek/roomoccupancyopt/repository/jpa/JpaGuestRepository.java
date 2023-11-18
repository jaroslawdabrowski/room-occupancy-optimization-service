package com.jarek.roomoccupancyopt.repository.jpa;

import com.jarek.roomoccupancyopt.repository.GuestRepository;
import com.jarek.roomoccupancyopt.repository.model.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGuestRepository extends GuestRepository, JpaRepository<GuestEntity, String> {
}
