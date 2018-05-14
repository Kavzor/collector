package nu.rolandsson.collector.model;

import nu.rolandsson.collector.contract.Builder;

public class Weather {
  
  private float windspeed;
  private float tempature;
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
  
  
  public static Weather create(Builder<WeatherBuilder> builder) {
    WeatherBuilder weatherBuilder = new WeatherBuilder();
    weatherBuilder = builder.build(weatherBuilder);
    return weatherBuilder.evaluate();
  }
}
