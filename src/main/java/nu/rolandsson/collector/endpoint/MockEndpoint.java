package nu.rolandsson.collector.endpoint;

import nu.rolandsson.collector.CollectorApplication;
import nu.rolandsson.collector.exception.WeatherException;
import nu.rolandsson.collector.mock.WeatherProvider;
import nu.rolandsson.collector.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "mock-service")
//@CrossOrigin(origins = "*")
public class MockEndpoint {
  private final static Logger logger =
          Logger.getLogger(MockEndpoint.class.getName());
  
  /*@Autowired
  private WeatherRepository weatherRepository;
  */
  private WeatherProvider mWeatherProvider;
  
  @Autowired
  public void context(ApplicationContext context) {
    mWeatherProvider = context.getBean(WeatherProvider.class);
  }
  
  @GetMapping("weathers/{index}")
  public ResponseEntity<Weather> mock_weather(@PathVariable("index")
                                              int index) {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
      try {
        var weather = mWeatherProvider.getWeather(index);
        return ResponseEntity.ok(weather);
      }
      catch (WeatherException ex) {
        var exceptionHeader = "Weather index " +
                index + "not found, " + ex.getWeatherSize() + " weathers available";
        return ResponseEntity
                .notFound()
                .header("exception", exceptionHeader)
                .build();
      }
    }
    else {
      logger.warning("Attempted to access mock in production");
      var exceptionHeader = "mock-service not available in production";
      return ResponseEntity
              .noContent()
              .header("exception", exceptionHeader)
              .build();
    }
  }
  
  @GetMapping("weathers")
  public ResponseEntity<List<Weather>> mock_weathers() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
      var weathers = mWeatherProvider.getWeathers();
      return ResponseEntity
              .ok()
              .header("mode", "development")
              .body(weathers);
    }
    else {
      return ResponseEntity
              .noContent()
              .header("mode", "production")
              .build();
    }
  }
  
  
  @GetMapping("traffic")
  public void mock_traffic() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
    
    }
  }
 
}
