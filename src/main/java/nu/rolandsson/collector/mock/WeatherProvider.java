package nu.rolandsson.collector.mock;


import nu.rolandsson.collector.exception.WeatherException;
import nu.rolandsson.collector.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * Responsible for providing weather through the assigned weatherservice
 */
@Component
public class WeatherProvider {
  
  private static final Logger logger =
          Logger.getLogger(WeatherProvider.class.getName());
  
  private final WeatherService mWeatherService;
  
  @Autowired
  public WeatherProvider(WeatherService weatherService) {
    mWeatherService = weatherService;
  }
  
  /**
   * Fetches weather with specific index value
   * @param index the index value of weatherdata
   * @return  the weather response
   * @throws WeatherException if weather index does not match a weather
   */
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
  
  /**
   * Fetches all weather data
   * @return the weather data
   */
  public List<Weather> getWeathers() {
    return mWeatherService.getAll();
  }
}
