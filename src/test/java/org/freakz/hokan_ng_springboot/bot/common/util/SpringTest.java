package org.freakz.hokan_ng_springboot.bot.common.util;

import org.freakz.hokan_ng_springboot.bot.common.api.DirectoryChangedHandler;
import org.freakz.hokan_ng_springboot.bot.common.api.DirectoryWatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Petri Airio (petri.j.airio@gmail.com) 02/09/16 / 10:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest implements DirectoryChangedHandler {

    private final String testDirectory = "/tmp/test/";
    @Autowired
    private DirectoryWatcher directoryWatcher;

    @Before
    public void init() {
        File f = new File(testDirectory);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    @Test
    public void testTest() {
        directoryWatcher.startWatching(this, testDirectory);

        Thread changeMaker = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String rndName = UUID.randomUUID().toString();
                createFile(rndName);
            }

            private void createFile(String rndName) {
                File file = new File(testDirectory + rndName);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        changeMaker.start();
        directoryWatcher.waitForChange();

    }

    @Override
    public void fileCreated(String file) {
        // log.debug("File created: {}", file);
    }
}
