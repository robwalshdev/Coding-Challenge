# Coding Challenge

Return information about a location (latitude & longitude) using [here api](https://developer.here.com/documentation).

## Instructions

Add API Key to `src/main/resources/application.yml`

Start the Spring Boot App

```bash
./mvnw spring-boot:run
```

## Usage
### Finds the nearest address for given coordinates

```
http://localhost:8080/location/address?latitude=<latitude>&longitude=<longitude>
```
Example - Request:
```bash
http://localhost:8080/location/address?latitude=40.689&longitude=-74.044
```

Example - Response:
```json
{
    "title": "Statue of Liberty",
    "address": {
        "label": "Statue of Liberty, New York, NY 10004, United States",
        "countryName": "United States",
        "county": "New York",
        "city": "New York",
        "district": "Liberty Island"
    },
    "distance": 47
}
```

### Addresses within a radius of the coordinates

```
http://localhost:8080/location/nearby?latitude=<latitude>&longitude=<longitude>&radius=<radius-meters>&limit=<limit>
```

Example - Request:
```bash
http://localhost:8080/location/nearby?latitude=40.7&longitude=-74.1&radius=250&limit=2
```

Example - Response:
```json
{
    "items": [
        {
            "title": "340 Winfield Ave, Jersey City, NJ 07305-1746, United States",
            "address": {
                "label": "340 Winfield Ave, Jersey City, NJ 07305-1746, United States",
                "countryName": "United States",
                "county": "Hudson",
                "city": "Jersey City",
                "district": "Greenville"
            },
            "distance": 24
        },
        {
            "title": "245 Bartholdi Ave, Jersey City, NJ 07305-1857, United States",
            "address": {
                "label": "245 Bartholdi Ave, Jersey City, NJ 07305-1857, United States",
                "countryName": "United States",
                "county": "Hudson",
                "city": "Jersey City",
                "district": "Greenville"
            },
            "distance": 30
        }
    ]
}
```

### Search for a given category (e.g. restaurant) within a radius of the coordinates
```
http://localhost:8080/location/category?latitude=<latitude>&longitude=<longitude>&radius=<radius-meters>&category=<category>
```

Example - Request:
```bash
http://localhost:8080/location/category?latitude=40.689&longitude=-74.044&radius=250&category=restaurant
```

Example - Response:
```json
{
    "items": [
        {
            "title": "Statue of Liberty Crown Café",
            "address": {
                "label": "Statue of Liberty Crown Café, New York, NY 10004, United States",
                "countryName": "United States",
                "county": "New York",
                "city": "New York",
                "district": "Liberty Island"
            },
            "distance": 238
        }
    ]
}
```