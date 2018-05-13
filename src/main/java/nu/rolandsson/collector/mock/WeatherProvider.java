package nu.rolandsson.collector.mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherProvider {
  
  private final WeatherService mWeatherService;
  
  @Autowired
  public WeatherProvider(WeatherService service) {
    System.out.println("Setting up weatherprovider");
    mWeatherService = service;
  }
  
  public String getWeather() {
    return mWeatherService.getWeather();
  }
}
