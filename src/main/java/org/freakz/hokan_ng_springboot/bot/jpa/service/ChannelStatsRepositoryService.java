package org.freakz.hokan_ng_springboot.bot.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.jpa.entity.Channel;
import org.freakz.hokan_ng_springboot.bot.jpa.entity.ChannelStats;
import org.freakz.hokan_ng_springboot.bot.jpa.repository.ChannelStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Petri Airio on 8.4.2015.
 * -
 */
@Service
@Slf4j
public class ChannelStatsRepositoryService implements ChannelStatsService {

    private final ChannelStatsRepository repository;

    @Autowired
    public ChannelStatsRepositoryService(ChannelStatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChannelStats findFirstByChannel(Channel channel) {
        return repository.findFirstByChannel(channel);
    }

    @Override
    public ChannelStats save(ChannelStats channelStats) {
        try {
            ChannelStats saved = repository.save(channelStats);
            return saved;
        } catch (Exception e) {
            log.error("Save failed...", e);
            log.error("... Failed entity: {}", channelStats.toString());
        }
        return channelStats;
    }

}
