package com.robert.location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;

import com.robert.location.model.Address;
import com.robert.location.model.HereData;
import com.robert.location.model.Location;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@RestClientTest(LocationService.class)
public class LocationServiceTests {

  @Autowired
  private LocationService locationService;

  @MockBean
  private RestTemplate restTemplate;

  private Location mockLocation;

  private HereData mockHereData;

  @Before
  public void setup() {
    Address address = new Address("Statue of Liberty, New York, NY 10004, United States",
        "United States", "New York", "New York", "Liberty Island");
    mockLocation = new Location("Statue of Liberty", address, 10);
    mockHereData = new HereData(Collections.singletonList(mockLocation));

    Mockito
        .when(restTemplate.getForEntity(anyString(), anyObject()))
        .thenReturn(new ResponseEntity<>(mockHereData, HttpStatus.OK));
  }

  @Test
  public void getAddressFromCoordinatesTest() {
    Location location = locationService.getAddressFromCoordinates(40.689253199999996,
        -74.04454817144321);
    assertEquals(mockLocation, location);
  }

  @Test
  public void getAddressesWithinRadiusTest() {
    HereData hereData = locationService.getAddressesWithinRadius(40.689253199999996,
        -74.04454817144321, 25, 1);
    assertEquals(mockHereData, hereData);
  }

  @Test
  public void getAddressesByCategoryTest() {
    HereData hereData = locationService.getAddressesByCategory(40.689253199999996,
        -74.04454817144321, 25, "shops");
    assertEquals(mockHereData, hereData);
  }

}
