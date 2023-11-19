package com.jarek.roomoccupancyopt.controller.model;

import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoomOccupancyCalculationRepresentation {
    @NotNull
    private RoomTypeUsageRepresentation economy;

    @NotNull
    private RoomTypeUsageRepresentation premium;

    public static RoomOccupancyCalculationRepresentation of(RoomOccupancyCalculation roomOccupancyCalculation) {
        return RoomOccupancyCalculationRepresentation.builder()
                .economy(RoomTypeUsageRepresentation.of(roomOccupancyCalculation.economy()))
                .premium(RoomTypeUsageRepresentation.of(roomOccupancyCalculation.premium()))
                .build();
    }

}
