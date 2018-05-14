package nu.rolandsson.collector;


import nu.rolandsson.collector.mock.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MockConfig {
  
  @Bean
  WeatherService mockWeatherService() {
    return new WeatherService() {
      @Override
      public String getWeather() {
        return "Clear blue sky today, not tomrrow";
      }
    };
  }
}
