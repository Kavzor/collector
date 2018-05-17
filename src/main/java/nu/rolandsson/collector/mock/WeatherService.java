package nu.rolandsson.collector.mock;

import nu.rolandsson.collector.model.Weather;
import nu.rolandsson.collector.model.repository.WeatherRepository;

import java.util.List;

public interface WeatherService {
  Weather get(int index);
  List<Weather> getAll();
  default void add(Weather weather) {}
  default void remove(Weather weather) {}
}
