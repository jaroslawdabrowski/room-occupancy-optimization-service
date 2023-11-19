package com.jarek.roomoccupancyopt.business;

import com.jarek.roomoccupancyopt.model.Guest;
import com.jarek.roomoccupancyopt.model.HotelAvailability;
import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import com.jarek.roomoccupancyopt.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomOccupancyCalculationServiceTest {

    @Mock
    private GuestRepository guestRepository;

    private RoomOccupancyCalculationService service;

    @BeforeEach
    void setUp() {
        List<Guest> guests = List.of(
          new Guest("01", 23),
          new Guest("02", 45),
          new Guest("03", 155),
          new Guest("04", 374),
          new Guest("05", 22),
          new Guest("06", 99),
          new Guest("07", 100),
          new Guest("08", 101),
          new Guest("09", 115),
          new Guest("10", 209)
        );
        when(guestRepository.findAll()).thenReturn(guests);

        service = new RoomOccupancyCalculationService(100, guestRepository);
    }

    @Test
    void shouldReturn0CalculationWhenNoRooms() {
        // given
        HotelAvailability hotelAvailabilityData = new HotelAvailability(0, 0);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(0, result.premium().getOccupied());
        assertEquals(0, result.premium().getTotalIncome());
        assertEquals(0, result.economy().getOccupied());
        assertEquals(0, result.economy().getTotalIncome());
    }

    @Test
    void shouldFillAllRoomsWhenMoreGuestsThenRooms() {
        // given
        HotelAvailability hotelAvailabilityData = new HotelAvailability(3, 3);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(3, result.premium().getOccupied());
        assertEquals(738, result.premium().getTotalIncome());
        assertEquals(3, result.economy().getOccupied());
        assertEquals(167, result.economy().getTotalIncome());
    }

    @Test
    void shouldFillAllocateAllGuestsWhenMoreRoomsThenGuests() {
        // given
        HotelAvailability hotelAvailabilityData = new HotelAvailability(7, 5);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(6, result.premium().getOccupied());
        assertEquals(1054, result.premium().getTotalIncome());
        assertEquals(4, result.economy().getOccupied());
        assertEquals(189, result.economy().getTotalIncome());
    }

    @Test
    void shouldNotSellEconomyRoomsToHighBudgetGuests() {
        // given
        HotelAvailability hotelAvailabilityData = new HotelAvailability(2, 7);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(2, result.premium().getOccupied());
        assertEquals(583, result.premium().getTotalIncome());
        assertEquals(4, result.economy().getOccupied());
        assertEquals(189, result.economy().getTotalIncome());
    }

    @Test
    void shouldUpgradeBestBudgetGuestsWhenNotEnoughEconomyRooms() {
        // given
        HotelAvailability hotelAvailabilityData = new HotelAvailability(10, 1);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(7, result.premium().getOccupied());
        assertEquals(1153, result.premium().getTotalIncome());
        assertEquals(1, result.economy().getOccupied());
        assertEquals(45, result.economy().getTotalIncome());
    }
}