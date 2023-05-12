package com.hackerrank.weather.exceptions;

public class WeatherNotFoundException extends RuntimeException{

  public WeatherNotFoundException(String message) {
    super(message);
  }

}
