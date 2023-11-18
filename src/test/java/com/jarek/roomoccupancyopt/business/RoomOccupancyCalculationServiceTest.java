package com.jarek.roomoccupancyopt.business;

import com.jarek.roomoccupancyopt.business.model.HotelAvailabilityData;
import com.jarek.roomoccupancyopt.model.RoomOccupancyCalculation;
import com.jarek.roomoccupancyopt.repository.GuestRepository;
import com.jarek.roomoccupancyopt.repository.model.GuestEntity;
import com.jarek.roomoccupancyopt.repository.model.mock.GuestEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        List<GuestEntity> guests = List.of(
          new GuestEntityMock("01", 23),
          new GuestEntityMock("02", 45),
          new GuestEntityMock("03", 155),
          new GuestEntityMock("04", 374),
          new GuestEntityMock("05", 22),
          new GuestEntityMock("06", 99),
          new GuestEntityMock("07", 100),
          new GuestEntityMock("08", 101),
          new GuestEntityMock("09", 115),
          new GuestEntityMock("10", 209)
        );
        when(guestRepository.findAll()).thenReturn(guests);

        service = new RoomOccupancyCalculationService(100, guestRepository);
    }

    @Disabled
    @Test
    void shouldReturn0CalculationWhenNoRooms() {
        // given
        HotelAvailabilityData hotelAvailabilityData = new HotelAvailabilityData(0, 0);

        // when
        RoomOccupancyCalculation result = service.calculate(hotelAvailabilityData);

        // then
        assertEquals(0, result.economy().occupied());
        assertEquals(0, result.economy().totalIncome());
        assertEquals(0, result.premium().occupied());
        assertEquals(0, result.premium().totalIncome());
    }
}