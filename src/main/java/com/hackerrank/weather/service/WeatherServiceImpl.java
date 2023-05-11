package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.sun.source.tree.TryTree;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
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
  public Optional <Weather> getWeatherById( Integer weatherId) {
    try {
      Weather weather = weatherRepository.getOne(weatherId);
      return Optional.of(weather);
    } catch (EntityNotFoundException e) {
      return Optional.empty();
    }
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
