package nu.rolandsson.collector.endpoint;

import nu.rolandsson.collector.CollectorApplication;
import nu.rolandsson.collector.MockConfig;
import nu.rolandsson.collector.mock.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
  public String mock_weather() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
      //var weatherProvider = mContext.getBean(WeatherProvider.class);
      return mWeatherProvider.getWeather();
    }
    else {
      return "Development mode is offline";
    }
  }
  
  @GetMapping("traffic")
  public void mock_traffic() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
    
    }
  }
 
}
