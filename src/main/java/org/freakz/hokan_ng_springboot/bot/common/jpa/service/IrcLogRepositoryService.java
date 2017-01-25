package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.IrcLog;
import org.freakz.hokan_ng_springboot.bot.common.jpa.repository.IrcLogRepository;
import org.freakz.hokan_ng_springboot.bot.common.models.StartAndEndTime;
import org.freakz.hokan_ng_springboot.bot.common.util.StringStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by Petri Airio on 21.8.2015.
 */
@Service
@Slf4j
public class IrcLogRepositoryService implements IrcLogService {

    @Autowired
    private IrcLogRepository repository;

    @Override
    @Transactional(readOnly = false)
    public IrcLog addIrcLog(Date timeStamp, String sender, String target, String message) {
        IrcLog log = new IrcLog();
        log.setTimeStamp(timeStamp);
        log.setSender(sender);
        log.setTarget(target);
        log.setMessage(message);
        return repository.save(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IrcLog> findMatchingLogRows(String logPattern) {
        List<IrcLog> allRows = repository.findAll();
        List<IrcLog> matching = new ArrayList<>();
        for (IrcLog log : allRows) {
            String logString = log.toString();
            if (StringStuff.match(logString, ".*" + logPattern + ".*", true)) {
                matching.add(log);
            }
        }
        return sortLogs(matching);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IrcLog> findByTimeStampBetween(StartAndEndTime saet) {
        ZonedDateTime zdtStart = ZonedDateTime.of(saet.getStartTime(), ZoneId.systemDefault());
        GregorianCalendar calStart = GregorianCalendar.from(zdtStart);

        ZonedDateTime zdtEnd = ZonedDateTime.of(saet.getEndTime(), ZoneId.systemDefault());
        GregorianCalendar calEnd = GregorianCalendar.from(zdtEnd);

        return repository.findByTimeStampBetween(calStart.getTime(), calEnd.getTime());
    }

    @Override
    public List<IrcLog> findByTimeStampBetweenAndTargetContaining(StartAndEndTime saet, String target) {
        ZonedDateTime zdtStart = ZonedDateTime.of(saet.getStartTime(), ZoneId.systemDefault());
        GregorianCalendar calStart = GregorianCalendar.from(zdtStart);

        ZonedDateTime zdtEnd = ZonedDateTime.of(saet.getEndTime(), ZoneId.systemDefault());
        GregorianCalendar calEnd = GregorianCalendar.from(zdtEnd);

        return repository.findByTimeStampBetweenAndTargetContaining(calStart.getTime(), calEnd.getTime(), target);
    }

    private List<IrcLog> sortLogs(List<IrcLog> allRows) {
        Comparator<? super IrcLog> comparator = (o1, o2) -> o1.getTimeStamp().compareTo(o2.getTimeStamp());
        allRows.sort(comparator);
        return allRows;
    }

    @Override
    public List<IrcLog> findByTarget(String target) {
        return repository.findByTarget(target);
    }
}
