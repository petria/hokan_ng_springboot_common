package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

public interface DataValuesService {

    String getValue(String nick, String network, String key);

    void setValue(String nick, String network, String key, String value);

}
