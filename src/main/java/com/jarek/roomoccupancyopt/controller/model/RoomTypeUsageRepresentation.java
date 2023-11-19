package com.jarek.roomoccupancyopt.controller.model;

import com.jarek.roomoccupancyopt.model.RoomTypeUsage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoomTypeUsageRepresentation {
    @NotNull
    @PositiveOrZero
    private int occupied;

    @NotNull
    @PositiveOrZero
    private int free;

    @NotNull
    @PositiveOrZero
    private int totalIncome;

    public static RoomTypeUsageRepresentation of(RoomTypeUsage roomTypeUsage) {
        return RoomTypeUsageRepresentation.builder()
                .occupied(roomTypeUsage.getOccupied())
                .free(roomTypeUsage.getFree())
                .totalIncome(roomTypeUsage.getTotalIncome())
                .build();
    }
}
