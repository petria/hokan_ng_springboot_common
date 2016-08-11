package org.freakz.hokan_ng_springboot.bot.models;

import java.io.Serializable;

/**
 * Created by Petri Airio on 11.8.2016.
 * -
 */
public class HourlyWeatherData implements Serializable {

  private String[] times;
  private String[] symbols;
  private String[] temperatures;

  public HourlyWeatherData() {
  }

  public String[] getTimes() {
    return times;
  }

  public void setTimes(String[] times) {
    this.times = times;
  }

  public String[] getSymbols() {
    return symbols;
  }

  public void setSymbols(String[] symbols) {
    this.symbols = symbols;
  }

  public String[] getTemperatures() {
    return temperatures;
  }

  public void setTemperatures(String[] temperatures) {
    this.temperatures = temperatures;
  }
}
