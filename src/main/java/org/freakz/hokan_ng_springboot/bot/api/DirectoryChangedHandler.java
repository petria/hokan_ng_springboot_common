package org.freakz.hokan_ng_springboot.bot.api;

/**
 * Created by Petri Airio (petri.airio@gmail.com) 01/09/16 / 14:20
 */
public interface DirectoryChangedHandler {

    void fileCreated(String file);

}
