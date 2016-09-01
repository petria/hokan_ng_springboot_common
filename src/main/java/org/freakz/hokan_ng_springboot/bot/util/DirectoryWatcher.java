package org.freakz.hokan_ng_springboot.bot.util;

import org.freakz.hokan_ng_springboot.bot.api.DirectoryChangedHandler;

/**
 * Created by Petri Airio (petri.airio@gmail.com) 01/09/16 / 14:18
 */
public class DirectoryWatcher {

    private final String directory;

    public DirectoryWatcher(String directory) {
        this.directory = directory;
    }

    public void startWatching(DirectoryChangedHandler directoryChangedHandler) {

    }


}
