package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.models.CommandStats;

/**
 * Created by Petri Airio (petri.j.airio@gmail.com) 25/11/2016 / 10.22
 */
public interface CommandStatsService {


    void statCommand(String network, String channel, String nick, String command);

    CommandStats getCommandStats(String network, String channel);


}
