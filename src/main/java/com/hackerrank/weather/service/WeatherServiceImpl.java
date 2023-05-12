package com.hackerrank.weather.service;

import com.hackerrank.weather.exceptions.WeatherNotFoundException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

  private final WeatherRepository weatherRepository;

  public WeatherServiceImpl(WeatherRepository weatherRepository) {
    this.weatherRepository = weatherRepository;
  }

  @Override
  public Weather getWeatherById( Integer id) {
    Optional<Weather> optionalWeather = weatherRepository.findById(id);
    if (optionalWeather.isEmpty()) {
      throw new WeatherNotFoundException("Weather not found with id: " + id);
    }
    return optionalWeather.get();
  }

  @Override
  public List<Weather> getWeathers() {
    return weatherRepository.findAll(Sort.by(Direction.ASC, "id"));
  }

  @Override
  public Weather createWeather(Weather weather) {
    return weatherRepository.save(weather);
  }

  @Override
  public boolean deleteWeather(Integer weatherId) {
    Optional<Weather> optionalWeather = weatherRepository.findById(weatherId);
    if (optionalWeather.isPresent()) {
      weatherRepository.deleteById(weatherId);
      return true;
    } else {
      return false;
    }
  }
}
