package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class RoomTypeUsage {
    private @NotNull @PositiveOrZero int occupied;

    private @NotNull @PositiveOrZero int free;

    private @NotNull @PositiveOrZero int totalIncome;

    public RoomTypeUsage(int free) {
        this(0, free, 0);
    }

    public boolean addGuest(Guest guest) {
        if (free == 0) {
            return false;
        }
        occupied++;
        free--;
        totalIncome += guest.budget();
        return true;
    }

    public boolean hasFreeRooms() {
        return free > 0;
    }
}
