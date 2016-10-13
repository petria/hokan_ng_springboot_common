package org.freakz.hokan_ng_springboot.bot.util;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.api.DirectoryChangedHandler;
import org.freakz.hokan_ng_springboot.bot.api.DirectoryWatcher;
import org.freakz.hokan_ng_springboot.bot.cmdpool.CommandPool;
import org.freakz.hokan_ng_springboot.bot.cmdpool.CommandRunnable;
import org.freakz.hokan_ng_springboot.bot.exception.HokanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by Petri Airio (petri.airio@gmail.com) 01/09/16 / 14:18
 */
@Component
@Scope("prototype")
@Slf4j
public class DirectoryWatcherImpl implements DirectoryWatcher {

    @Autowired
    private CommandPool commandPool;

    private final Object sync = new String("Sync");

    private boolean waitingForChange = false;

    public void waitForChange() {
        try {
            waitingForChange = true;
            synchronized (sync) {
                sync.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void startWatching(final DirectoryChangedHandler directoryChangedHandler, String watchFolder) {
        final Path path = Paths.get(watchFolder);
        log.debug("Watching folder: " + path);

        CommandRunnable runnable = new CommandRunnable() {
            @Override
            public void handleRun(long myPid, Object args) throws HokanException {
                WatchService service;
                try {
                    FileSystem fs = path.getFileSystem();
                    service = fs.newWatchService();
                    path.register(service, ENTRY_CREATE);
                } catch (Exception e) {
                    log.error("watch", e);
                    return;
                }

                // Start the infinite polling loop
                WatchKey key = null;
                while (true) {
                    try {
                        key = service.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        //
                    }
                    if (key == null) {
                        continue;
                    }

                    // Dequeueing events
                    WatchEvent.Kind<?> kind;
                    for (WatchEvent<?> watchEvent : key.pollEvents()) {
                        // Get the type of the event
                        kind = watchEvent.kind();
                        if (ENTRY_CREATE == kind) {
                            Path newPath = ((WatchEvent<Path>) watchEvent).context();
                            String file = path.toString() + "/" + newPath.getFileName();
                            try {
                                directoryChangedHandler.fileCreated(file);
                                if (waitingForChange) {
                                    synchronized (sync) {
                                        sync.notify();
                                    }
                                }
                            } catch (Exception e) {
                                log.error("Load telkku data", e);
                            }
                        }
                    }

                    if (!key.reset()) {
                        break; //loop
                    }
                }

            }
        };
        commandPool.startRunnable(runnable, "<system>");

    }


}
