package org.freakz.hokan_ng_springboot.bot.common.jpa.repository;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.IrcLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Petri Airio on 21.8.2015.
 */
public interface IrcLogRepository extends JpaRepository<IrcLog, Long> {

    List<IrcLog> findByTimeStampBetween(Date start, Date end);

    List<IrcLog> findByTimeStampBetweenAndTargetContaining(Date start, Date end, String target);

    List<IrcLog> findByTarget(String target);

}
