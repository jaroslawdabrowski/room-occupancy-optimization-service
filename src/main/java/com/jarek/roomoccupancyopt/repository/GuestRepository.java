package com.jarek.roomoccupancyopt.repository;

import com.jarek.roomoccupancyopt.repository.model.GuestEntity;

import java.util.List;

public interface GuestRepository {
    List<GuestEntity> findAll();
}
