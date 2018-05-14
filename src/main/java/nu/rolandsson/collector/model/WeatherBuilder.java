package nu.rolandsson.collector.model;

import nu.rolandsson.collector.contract.BuildEvaluator;
import nu.rolandsson.collector.contract.Builder;

public class WeatherBuilder implements BuildEvaluator<Weather> {
  
  private float mSpeed;
  private float mTempature;
  private String mDescription;
  
  public WeatherBuilder windspeed(float windspeed) {
    mSpeed = windspeed;
    return this;
  }
  
  public WeatherBuilder tempature(float tempature) {
    mTempature = tempature;
    return this;
  }
  
  public WeatherBuilder description(String description) {
    mDescription = description;
    return this;
  }
  
  @Override
  public Weather evaluate() {
    Weather weather = new Weather();
    weather.setDescription(mDescription);
    weather.setTempature(mTempature);
    weather.setWindspeed(mSpeed);
    return weather;
  }
}
