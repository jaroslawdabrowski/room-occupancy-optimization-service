package com.jarek.roomoccupancyopt.controller.model;

import com.jarek.roomoccupancyopt.model.HotelAvailability;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Room Occupancy Optimization Calculation Request")
public class RoomOccupancyOptimizationCalculationRequest {

    @Valid
    @NotNull
    @Schema(description = "Economy room setup definition")
    private RoomTypeUsageRequest economy;

    @Valid
    @NotNull
    @Schema(description = "Premium room setup definition")
    private RoomTypeUsageRequest premium;

    public HotelAvailability toHotelAvailability() {
        return new HotelAvailability(premium.getFree(), economy.getFree());
    }

}
