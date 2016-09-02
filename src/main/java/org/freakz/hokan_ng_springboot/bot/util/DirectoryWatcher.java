package org.freakz.hokan_ng_springboot.bot.util;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.api.DirectoryChangedHandler;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Petri Airio (petri.airio@gmail.com) 01/09/16 / 14:18
 */
@Component
@Slf4j
public class DirectoryWatcher {

    private final String watchFolder;

    public DirectoryWatcher() {
        this.watchFolder = "/tmp/";
    }

    public DirectoryWatcher(String watchFolder) {
        this.watchFolder = watchFolder;
    }

    public void startWatching(DirectoryChangedHandler directoryChangedHandler) {
        final Path path = Paths.get(watchFolder);
        log.debug("Watching folder: " + path);

    }


}
