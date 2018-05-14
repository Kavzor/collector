package nu.rolandsson.collector;


import nu.rolandsson.collector.mock.WeatherService;
import nu.rolandsson.collector.model.Weather;
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
      public Weather getWeather() {
        return Weather.create(weather -> {
          return weather
                  .windspeed(10)
                  .tempature(273)
                  .description("Sunny");
        });
      }
    };
  }
}
