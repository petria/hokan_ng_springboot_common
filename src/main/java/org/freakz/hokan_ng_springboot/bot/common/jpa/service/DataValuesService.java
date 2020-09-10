package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.models.DataValuesModel;

import java.util.List;
import java.util.Set;

public interface DataValuesService {

    List<DataValuesModel> getDataValues(String channel, String network, String key);

    String getValue(String nick, String channel, String network, String key);

    void setValue(String nick, String channel, String network, String key, String value);

    Set<String> getMatchingKeys(String keyLike);

}
