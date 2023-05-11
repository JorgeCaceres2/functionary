package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {

  private final WeatherService weatherService;

  public WeatherApiRestController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping()
  public ResponseEntity<List<Weather>> getWeathers() {
    List <Weather> weathers = weatherService.getWeathers();
    return ResponseEntity.ok().body(weathers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Weather> getWeatherById (@PathVariable Integer id) {
    Optional <Weather> optionalWeather = weatherService.getWeatherById(id);
    Weather weather = optionalWeather.orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather not found"));
    return ResponseEntity.ok().body(weather);
  }

  @PostMapping()
  public ResponseEntity<Weather> createWeather(@RequestBody Weather weather) {
    Weather createdWeather = weatherService.createWeather(weather);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdWeather);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteWeather (@PathVariable Integer id) {
      boolean successfullyDeleted = weatherService.deleteWeather(id);
      if (successfullyDeleted) {
        return ResponseEntity.noContent().build();
      } else {
        return ResponseEntity.notFound().build();
      }
  }
}
