package nu.rolandsson.collector.model.repository;

import nu.rolandsson.collector.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
