package nu.rolandsson.collector.model;

import nu.rolandsson.collector.contract.BuildEvaluator;
import nu.rolandsson.collector.contract.Builder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherBuilder implements BuildEvaluator<Weather> {
  
  private static final String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
  
  private float mSpeed;
  private float mTempature;
  private String mDescription;
  private String mTimestamp;
  
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
  
  public WeatherBuilder timestamp(String timestamp) {
    mTimestamp = timestamp;
    return this;
  }
  
  @Override
  public Weather evaluate() {
    Weather weather = new Weather();
    weather.setDescription(mDescription);
    weather.setTempature(mTempature);
    weather.setWindspeed(mSpeed);
  
    if(mTimestamp == null) {
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      weather.setTimestamp(sdf.format(new Date()));
    }
    else {
      weather.setTimestamp(mTimestamp);
    }
    return weather;
  }
}
