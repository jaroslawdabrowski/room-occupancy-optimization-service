package com.jarek.roomoccupancyopt.controller.model;

import com.jarek.roomoccupancyopt.model.RoomTypeUsage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Schema(description = "Room Type Usage")
public class RoomTypeUsageRepresentation {
    @NotNull
    @PositiveOrZero
    @Schema(description = "Number of occupied rooms", example = "6")
    private int occupied;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Number of free rooms", example = "3")
    private int free;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Total income based on occupied rooms", example = "1700")
    private int totalIncome;

    public static RoomTypeUsageRepresentation of(RoomTypeUsage roomTypeUsage) {
        return RoomTypeUsageRepresentation.builder()
                .occupied(roomTypeUsage.getOccupied())
                .free(roomTypeUsage.getFree())
                .totalIncome(roomTypeUsage.getTotalIncome())
                .build();
    }
}
