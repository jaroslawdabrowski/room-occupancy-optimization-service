package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record HotelAvailability(@NotNull @PositiveOrZero int premiumRooms,
                                @NotNull @PositiveOrZero int economyRooms) {
}
