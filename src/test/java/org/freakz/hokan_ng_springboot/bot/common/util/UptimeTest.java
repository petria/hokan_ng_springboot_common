package org.freakz.hokan_ng_springboot.bot.common.util;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class UptimeTest {

    @Test
    public void testUptime() {
        LocalDateTime now = LocalDateTime.now();
//        now.
        long millis = System.currentTimeMillis();

        Uptime uptime1 = new Uptime(now);
        Uptime uptime2 = new Uptime(millis);

        Date d = new Date(2100, 10, 10);

        Integer[] timeDiff1 = uptime1.getTimeDiff(d.getTime());
        Integer[] timeDiff2 = uptime2.getTimeDiff(d.getTime());

        int foo = 0;
    }

}
