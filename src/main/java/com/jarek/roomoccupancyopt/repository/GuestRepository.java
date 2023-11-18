package com.jarek.roomoccupancyopt.repository;

import com.jarek.roomoccupancyopt.model.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAllGuests();
}
