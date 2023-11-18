package com.jarek.roomoccupancyopt.business.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record HotelAvailabilityData(@NotNull @PositiveOrZero int premiumRooms,
                                    @NotNull @PositiveOrZero int economyRooms) {
}
