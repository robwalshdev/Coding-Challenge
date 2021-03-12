package com.robert.location.controller;

import com.robert.location.model.HereData;
import com.robert.location.model.Location;
import com.robert.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

  @Autowired
  private LocationService locationService;

  /**
   * GET request - Finds the nearest address for given coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @return Location of nearest Address
   */
  @GetMapping("/location/address")
  public Location getAddressFromCoordinates(@RequestParam Double latitude,
      @RequestParam Double longitude) {
    return locationService.getAddressFromCoordinates(latitude, longitude);
  }

  /**
   * GET request - Addresses within a radius of the coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @param radius    int - in meters
   * @param limit     int - the number of results
   * @return HereData - Addresses found within radius
   */
  @GetMapping("/location/nearby")
  public HereData getAddressesWithinRadius(@RequestParam Double latitude,
      @RequestParam Double longitude, @RequestParam int radius,
      @RequestParam(defaultValue = "25") int limit) {
    return locationService.getAddressesWithinRadius(latitude, longitude, radius, limit);
  }

  /**
   * GET request - Search for a given category (e.g. shops) within a radius of the coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @param radius    int - in meters
   * @param category  String - of places of search
   * @return HereData - Addresses found for given category
   */
  @GetMapping("/location/category")
  public HereData getAddressesByCategory(@RequestParam Double latitude,
      @RequestParam Double longitude, @RequestParam int radius, @RequestParam String category) {
    return locationService.getAddressesByCategory(latitude, longitude, radius, category);
  }

}
