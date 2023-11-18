package com.jarek.roomoccupancyopt.business;

import com.jarek.roomoccupancyopt.model.Guest;
import com.jarek.roomoccupancyopt.model.HotelAvailability;
import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import com.jarek.roomoccupancyopt.model.RoomTypeUsage;
import com.jarek.roomoccupancyopt.repository.GuestRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.PriorityQueue;
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

    public RoomOccupancyCalculation calculate(@NotNull HotelAvailability hotelAvailability) {
        var premiumRoomUsage = new RoomTypeUsage(hotelAvailability.premiumRooms());
        var economyRoomUsage = new RoomTypeUsage(hotelAvailability.economyRooms());

        var allGuests = guestRepository.findAllGuests();

        var highBudgetGuests = new PriorityQueue<>(Comparator.comparing(Guest::budget).reversed());
        allGuests.stream().filter(g -> g.budget() >= hotelPremiumThreshold).forEach(highBudgetGuests::offer);

        var lowBudgetGuests = new PriorityQueue<>(Comparator.comparing(Guest::budget).reversed());
        allGuests.stream().filter(g -> g.budget() < hotelPremiumThreshold).forEach(lowBudgetGuests::offer);

        // allocate best paying guests to premium rooms first
        while (premiumRoomUsage.hasFreeRooms() && !highBudgetGuests.isEmpty()) {
            premiumRoomUsage.addGuest(highBudgetGuests.poll());
        }

        // we don't have sufficient amount of economy rooms, allocate best paying low budget guest to premium room
        if (lowBudgetGuests.size() - hotelAvailability.economyRooms() > 0
                && premiumRoomUsage.hasFreeRooms()) {
            premiumRoomUsage.addGuest(lowBudgetGuests.poll());
        }

        // allocate budget guests to economy rooms
        while (economyRoomUsage.hasFreeRooms() && !lowBudgetGuests.isEmpty()) {
            economyRoomUsage.addGuest(lowBudgetGuests.poll());
        }

        return new RoomOccupancyCalculation(premiumRoomUsage, economyRoomUsage);
    }
}
