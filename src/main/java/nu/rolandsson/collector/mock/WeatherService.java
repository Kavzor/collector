package nu.rolandsson.collector.mock;

import nu.rolandsson.collector.model.Weather;

import java.util.List;

public interface WeatherService {
  Weather get(int index);
  List<Weather> getAll();
}
