package com.robert.location.service;

import com.robert.location.model.HereData;
import com.robert.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

  /**
   * API Key form application.properties
   */
  @Value("${api.key}")
  private String apiKey;

  /**
   * RestTemplate for executing HTTP Requests
   */
  @Autowired
  private RestTemplate restTemplate;

  /**
   * Finds the nearest address for given coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @return Location of nearest Address
   */
  public Location getAddressFromCoordinates(Double latitude, Double longitude) {
    String requestUrl = "https://revgeocode.search.hereapi.com/v1/revgeocode?at=" + latitude +
        "," + longitude + "&apiKey=" + apiKey;

    HereData hereData = getEntityFromRequest(requestUrl);

    return hereData.getItems().get(0);
  }

  /**
   * Addresses within a radius of the coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @param radius    int - in meters
   * @param limit     int - the number of results
   * @return HereData - Addresses found within radius
   */
  public HereData getAddressesWithinRadius(Double latitude, Double longitude, int radius,
      int limit) {
    String requestUrl = "https://revgeocode.search.hereapi.com/v1/revgeocode?in=circle:" +
        latitude + "," + longitude + ";r=" + radius + "&limit=" + limit + "&apiKey=" + apiKey;

    return getEntityFromRequest(requestUrl);
  }

  /**
   * Search for a given category (e.g. shops) within a radius of the coordinates
   *
   * @param latitude  Double
   * @param longitude Double
   * @param radius    int - in meters
   * @param category  String - of places of search
   * @return HereData - Addresses found for given category
   */
  public HereData getAddressesByCategory(Double latitude, Double longitude, int radius,
      String category) {
    String requestUrl = "https://revgeocode.search.hereapi.com/v1/discover?in=circle:" +
        latitude + "," + longitude + ";r=" + radius + "&q=" + category + "&apiKey=" + apiKey;

    return getEntityFromRequest(requestUrl);
  }

  /**
   * Helper method which gets the entity and casts to HereData
   *
   * @param request to get entity
   * @return HereData
   */
  private HereData getEntityFromRequest(String request) {
    ResponseEntity responseEntity = restTemplate.getForEntity(request, HereData.class);

    HereData hereData =
      responseEntity.getStatusCode() == HttpStatus.OK ? (HereData) responseEntity.getBody()
          : null;

    return hereData;
  }
}
