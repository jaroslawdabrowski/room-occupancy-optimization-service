package com.jarek.roomoccupancyopt.repository.jpa;

import com.jarek.roomoccupancyopt.model.Guest;
import com.jarek.roomoccupancyopt.repository.GuestRepository;
import com.jarek.roomoccupancyopt.repository.jpa.model.GuestEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaGuestRepositoryFacade implements GuestRepository {

    private final JpaGuestRepository jpaGuestRepository;

    public JpaGuestRepositoryFacade(JpaGuestRepository jpaGuestRepository) {
        this.jpaGuestRepository = jpaGuestRepository;
    }

    @Override
    public List<Guest> findAll() {
        return jpaGuestRepository.findAll().stream().map(GuestEntity::toGuest).collect(Collectors.toList());
    }
}
