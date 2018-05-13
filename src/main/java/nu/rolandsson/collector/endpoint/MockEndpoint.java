package nu.rolandsson.collector.endpoint;

import nu.rolandsson.collector.CollectorApplication;
import nu.rolandsson.collector.MockConfig;
import nu.rolandsson.collector.mock.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockEndpoint {
  
  private AnnotationConfigApplicationContext mContext;
  
  @Autowired
  public void context(AnnotationConfigApplicationContext context) {
    mContext = context;
    mContext.register(MockConfig.class);
    mContext.refresh();
  }
  
  @GetMapping
  public String mock_weather() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
      var weatherProvider = mContext.getBean(WeatherProvider.class);
      return weatherProvider.getWeather();
    }
    else {
      return "Development mode is offline";
    }
  }
  
  @GetMapping
  public void mock_traffic() {
    if(CollectorApplication.IS_DEVELOPMENT_MODE) {
    
    }
  }
 
}
