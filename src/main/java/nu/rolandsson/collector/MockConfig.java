package nu.rolandsson.collector;


import nu.rolandsson.collector.mock.WeatherService;
import nu.rolandsson.collector.model.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ComponentScan
public class MockConfig {
  
  @Bean
  WeatherService mockWeatherService() {
    return new WeatherService() {
      @Override
      public Weather get(int index) {
        return getAllWeatherData().get(index);
      }
  
      @Override
      public List<Weather> getAll() {
        return getAllWeatherData();
      }
    };
  }
  
  private List<Weather> getAllWeatherData() {
    return new LinkedList<>() {{
      add(Weather.create(weather -> weather
              .windspeed(4.7f)
              .tempature(273 + 17.0f)
              .description("Snowy")
              .timestamp("Tue, 15 May 2018 07:02:01 +0200")));
      add(Weather.create(weather -> weather
              .windspeed(3.4f)
              .tempature(273 + 10.9f)
              .description("Rain")
              .timestamp("Tue, 15 May 2018 22:05:23 +0200")));
      add(Weather.create(weather -> weather
              .windspeed(2.5f)
              .tempature(273 + 15.8f)
              .description("Cloudless")
              .timestamp("Wed, 16 May 2018 07:04:21 +0200")));
      add(Weather.create(weather -> weather
              .windspeed(12.3f)
              .tempature(273 + 23.4f)
              .description("Sunny")
              .timestamp("Wed, 16 May 2018 22:01:43 +0200")));
    }};
  }
}
