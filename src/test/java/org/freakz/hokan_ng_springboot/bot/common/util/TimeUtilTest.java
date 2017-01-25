package org.freakz.hokan_ng_springboot.bot.common.util;

import org.freakz.hokan_ng_springboot.bot.common.models.StartAndEndTime;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by Petri Airio on 21.8.2015.
 * -
 */
public class TimeUtilTest {

    @Test
    public void testTimeUtil() {
        LocalDateTime now = LocalDateTime.now();
        StartAndEndTime test = TimeUtil.getStartAndEndTimeForDay(now);
        Assert.assertTrue(true);
    }

    @Test
    public void testParseDateTime() {
        String dateString = "1.5.";
        LocalDateTime parsed = TimeUtil.parseDateTime(dateString);
        Assert.assertEquals("day of month", 1, parsed.getDayOfMonth());
        Assert.assertEquals("month of year", 5, parsed.getMonthValue());

        dateString = "9.6";
        parsed = TimeUtil.parseDateTime(dateString);
        Assert.assertEquals("day of month", 9, parsed.getDayOfMonth());
        Assert.assertEquals("month of year", 6, parsed.getMonthValue());

    }

}
