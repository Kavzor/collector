package nu.rolandsson.collector.exception;

import nu.rolandsson.collector.model.Weather;

import java.util.LinkedList;
import java.util.List;

public class WeatherException extends Exception {
  
  private List<Weather> mWeatherList = new LinkedList<>();
  
  public WeatherException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public WeatherException(Throwable cause, String message,
                          List<Weather> weatherList) {
    this(message, cause);
    mWeatherList = weatherList;
  }
  
  public List<Weather> getWeatherList() {
    return mWeatherList;
  }
  
  public int getWeatherSize() {
    return mWeatherList.size();
  }
}
