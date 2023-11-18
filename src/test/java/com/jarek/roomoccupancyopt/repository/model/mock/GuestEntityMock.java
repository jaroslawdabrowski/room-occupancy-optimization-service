package com.jarek.roomoccupancyopt.repository.model.mock;

import com.jarek.roomoccupancyopt.repository.model.GuestEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuestEntityMock implements GuestEntity {
    private final String id;
    private final int budget;
}
