package org.freakz.hokan_ng_springboot.bot.service.cityresolver;

import org.freakz.hokan_ng_springboot.bot.models.CityData;

/**
 * Created by Petri Airio on 12.8.2016.
 * -
 */
public interface CityResolver {

  CityData resolveCityNames(String query);

}
