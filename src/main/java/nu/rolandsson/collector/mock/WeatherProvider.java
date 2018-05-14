package nu.rolandsson.collector.mock;


import nu.rolandsson.collector.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherProvider {
  
  private final WeatherService mWeatherService;
  
  @Autowired
  public WeatherProvider(WeatherService service) {
    System.out.println("Setting up weatherprovider");
    mWeatherService = service;
  }
  
  public Weather getWeather() {
    return mWeatherService.getWeather();
  }
  
  public List<Weather> getWeathers() {
    return mWeatherService.getWeathers();
  }
}
