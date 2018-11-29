package org.freakz.hokan_ng_springboot.bot.common.service;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.IrcLog;
import org.freakz.hokan_ng_springboot.bot.common.models.StatsData;
import org.freakz.hokan_ng_springboot.bot.common.models.StatsMapper;
import org.freakz.hokan_ng_springboot.bot.common.util.StringStuff;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Petri Airio on 21.8.2015.
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Override
    public StatsMapper getDailyStatsForChannel(LocalDateTime day, String channel) {
        List<IrcLog> logs = null; // TODO ircLogService.findByTimeStampBetweenAndTargetContaining(TimeUtil.getStartAndEndTimeForDay(day), channel);
        StatsMapper statsMapper = new StatsMapper();
        if (logs.size() == 0) {
            ZonedDateTime zdt = ZonedDateTime.of(day, ZoneId.systemDefault());
            GregorianCalendar cal = GregorianCalendar.from(zdt);
            statsMapper.setError("No stats for day " + StringStuff.formatTime(cal.getTime(), StringStuff.STRING_STUFF_DF_DDMMYYYY)
                    + " and channel "
                    + channel);
        } else {
            processLogs(statsMapper, logs);
        }
        return statsMapper;
    }

    private void processLogs(StatsMapper statsMapper, List<IrcLog> logsForDay) {
        for (IrcLog ircLog : logsForDay) {
            String sender = ircLog.getSender();
            String[] words = ircLog.getMessage().split(" ");
            StatsData statsData = statsMapper.getStatsDataForNick(sender);
            statsData.addToLines(1);
            statsData.addToWords(words.length);
        }
    }

    public StatsMapper getStatsForChannel(String channel) {
        List<IrcLog> logs = null; // TODO ircLogService.findByTarget(channel);
        StatsMapper statsMapper = new StatsMapper();
        if (logs.size() == 0) {
            statsMapper.setError("No stats for channel: " + channel);
        } else {
            processLogs(statsMapper, logs);
        }
        return statsMapper;
    }
}
