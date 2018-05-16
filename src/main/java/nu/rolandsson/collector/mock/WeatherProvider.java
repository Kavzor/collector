package nu.rolandsson.collector.mock;


import nu.rolandsson.collector.exception.WeatherException;
import nu.rolandsson.collector.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class WeatherProvider {
  
  private static final Logger logger =
          Logger.getLogger(WeatherProvider.class.getName());
  
  private final WeatherService mWeatherService;
  
  @Autowired
  public WeatherProvider(WeatherService service) {
    mWeatherService = service;
  }
  
  public Weather getWeather(int index) throws WeatherException {
    try {
      return mWeatherService.get(index);
    }
    catch(IndexOutOfBoundsException ex) {
      logger.severe("Attempting to access index out of weather list");
      throw new WeatherException(ex,
              "Weather with index " + index + " does not exist",
              mWeatherService.getAll());
    }
  }
  
  public List<Weather> getWeathers() {
    return mWeatherService.getAll();
  }
}
