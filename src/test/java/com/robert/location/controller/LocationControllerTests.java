package com.robert.location.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import com.robert.location.model.Address;
import com.robert.location.model.HereData;
import com.robert.location.model.Location;
import com.robert.location.service.LocationService;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RestClientTest(LocationController.class)
public class LocationControllerTests {

  @Autowired
  private LocationController locationController;

  @MockBean
  private LocationService locationService;

  private Location mockLocation;

  private HereData mockHereData;

  @Before
  public void setup() {
    Address address = new Address("Statue of Liberty, New York, NY 10004, United States",
        "United States", "New York", "New York", "Liberty Island");
    mockLocation = new Location("Statue of Liberty", address, 10);

    mockHereData = new HereData(Collections.singletonList(mockLocation));
  }

  @Test
  public void getAddressFromCoordinatesTest() {
    Mockito
        .when(locationService.getAddressFromCoordinates(anyDouble(), anyDouble()))
        .thenReturn(mockLocation);

    Location location = locationController
        .getAddressFromCoordinates(40.689253199999996, -74.04454817144321);
    assertEquals(mockLocation, location);
  }

  @Test
  public void getAddressesWithinRadiusTest() {
    Mockito
        .when(
            locationService.getAddressesWithinRadius(anyDouble(), anyDouble(), anyInt(), anyInt()))
        .thenReturn(mockHereData);

    HereData hereData = locationController
        .getAddressesWithinRadius(40.689253199999996, -74.04454817144321,
            25, 1);

    assertEquals(mockHereData, hereData);
  }

  @Test
  public void getAddressesByCategoryTest() {
    Mockito
        .when(locationService.getAddressesByCategory(anyDouble(), anyDouble(), anyInt(),
            anyString()))
        .thenReturn(mockHereData);

    HereData hereData = locationController
        .getAddressesByCategory(40.689253199999996, -74.04454817144321,
            25, "statues");

    assertEquals(mockHereData, hereData);
  }

}
