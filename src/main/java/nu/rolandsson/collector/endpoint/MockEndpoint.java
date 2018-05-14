package nu.rolandsson.collector.endpoint;

import nu.rolandsson.collector.CollectorApplication;
import nu.rolandsson.collector.MockConfig;
import nu.rolandsson.collector.mock.WeatherProvider;
import nu.rolandsson.collector.model.Weather;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MockEndpoint {
  
  @Autowired
  private ApplicationContext mContext;
  
  private WeatherProvider mWeatherProvider;
  
  @Autowired
  public void context(ApplicationContext context) {
    mContext = context;
    mWeatherProvider = context.getBean(WeatherProvider.class);
  }
  
  @GetMapping("weather")
  public ResponseEntity<Weather> mock_weather() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
      var weather = mWeatherProvider.getWeather();
      return ResponseEntity
              .ok(weather);
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
