package nu.rolandsson.collector.endpoint;

import nu.rolandsson.collector.mock.WeatherProvider;
import nu.rolandsson.collector.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "weather-service")
public class WeatherEndpoint {
  
  
  private WeatherProvider mWeatherProvider;
  
  @Autowired
  public void context(ApplicationContext context) {
    mWeatherProvider = context.getBean(WeatherProvider.class);
  }
  
  @GetMapping("weathers")
  public ResponseEntity<List<Weather>> weathers() {
      var weathers = mWeatherProvider.getWeathers();
      return ResponseEntity
              .ok()
              .header("mode", "production")
              .body(weathers);
  }
}
