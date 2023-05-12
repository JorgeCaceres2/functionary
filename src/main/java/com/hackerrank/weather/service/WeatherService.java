package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import java.util.List;

public interface WeatherService {

  Weather getWeatherById(Integer weatherId);

  List<Weather> getWeathers ();

  Weather createWeather (Weather weather);

  boolean deleteWeather (Integer weatherId);





}
