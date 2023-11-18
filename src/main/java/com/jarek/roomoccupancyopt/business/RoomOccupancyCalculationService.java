package com.jarek.roomoccupancyopt.business;

import com.jarek.roomoccupancyopt.business.model.HotelAvailabilityData;
import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import com.jarek.roomoccupancyopt.model.RoomTypeUsage;
import com.jarek.roomoccupancyopt.repository.GuestRepository;
import com.jarek.roomoccupancyopt.repository.model.GuestEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomOccupancyCalculationService {
    private final int hotelPremiumThreshold;

    private final GuestRepository guestRepository;

    @Autowired
    public RoomOccupancyCalculationService(@Value("${hotel.premium.threshold}") int hotelPremiumThreshold,
                                           GuestRepository guestRepository) {
        this.hotelPremiumThreshold = hotelPremiumThreshold;
        this.guestRepository = guestRepository;
    }

    public RoomOccupancyCalculation calculate(@NotNull HotelAvailabilityData hotelAvailabilityData) {
        List<GuestEntity> guests = guestRepository.findAll();
        return new RoomOccupancyCalculation(new RoomTypeUsage(1, 10), new RoomTypeUsage(2, 10));
    }
}
