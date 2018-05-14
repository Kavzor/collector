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
      public Weather getWeather() {
        return Weather.create(weather -> weather
                .windspeed(10)
                .tempature(273)
                .description("Sunny"));
      }
  
      @Override
      public List<Weather> getWeathers() {
        return new LinkedList<Weather>() {{
          add(Weather.create(weather -> weather
                  .windspeed(5)
                  .tempature(15)
                  .description("Snowy")));
          add(Weather.create(weather -> weather
                  .windspeed(15)
                  .tempature(10)
                  .description("Rain")));
          add(Weather.create(weather -> weather
                  .windspeed(2.5f)
                  .tempature(15.8f)
                  .description("Cloudless")));
          add(Weather.create(weather -> weather
                  .windspeed(12.3f)
                  .tempature(23.4f)
                  .description("Sunny")));
        }};
      }
    };
  }
}
