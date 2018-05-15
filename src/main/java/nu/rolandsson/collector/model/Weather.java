package nu.rolandsson.collector.model;

import nu.rolandsson.collector.contract.BuildEvaluator;
import nu.rolandsson.collector.contract.Builder;

import java.util.Date;

public class Weather {
  
  private float windspeed;
  private float tempature;
  private String timestamp;
  private String description;
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public float getTempature() {
    return tempature;
  }
  
  public void setTempature(float tempature) {
    this.tempature = tempature;
  }
  
  public float getWindspeed() {
    return windspeed;
  }
  
  public void setWindspeed(float windspeed) {
    this.windspeed = windspeed;
  }
  
  public String getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
  
  
  public static Weather create(Builder<WeatherBuilder> builder) {
    var weather = new WeatherBuilder();
    return builder.build(weather).evaluate();
  }
}
