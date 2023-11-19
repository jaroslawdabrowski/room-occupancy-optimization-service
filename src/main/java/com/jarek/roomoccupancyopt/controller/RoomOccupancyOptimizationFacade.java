package com.jarek.roomoccupancyopt.controller;

import com.jarek.roomoccupancyopt.business.RoomOccupancyCalculationService;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyCalculationRepresentation;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyOptimizationCalculationRequest;
import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
@Component
class RoomOccupancyOptimizationFacade {

    private final RoomOccupancyCalculationService roomOccupancyCalculationService;

    RoomOccupancyOptimizationFacade(RoomOccupancyCalculationService roomOccupancyCalculationService) {
        this.roomOccupancyCalculationService = roomOccupancyCalculationService;
    }

    RoomOccupancyCalculationRepresentation calculate(@Valid @NotNull RoomOccupancyOptimizationCalculationRequest roomOccupancyOptimizationCalculationRequest) {
        RoomOccupancyCalculation roomOccupancyCalculation = roomOccupancyCalculationService.calculate(roomOccupancyOptimizationCalculationRequest.toHotelAvailability());
        return RoomOccupancyCalculationRepresentation.of(roomOccupancyCalculation);
    }
}
