package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.Channel;
import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.TvNotify;
import org.freakz.hokan_ng_springboot.bot.common.jpa.repository.TvNotifyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Petri Airio (petri.j.airio@gmail.com) on 26.4.2015.
 */
@Service

public class RepositoryTvNotifyService implements TvNotifyService {

    private static final Logger log = LoggerFactory.getLogger(RepositoryTvNotifyService.class);


    @Autowired
    private TvNotifyRepository repository;

    @Override
    public TvNotify addTvNotify(Channel channel, String pattern, String owner) {
        TvNotify notify = new TvNotify(pattern, owner, channel, false);
        notify.setNotifyDescription("");
        return repository.save(notify);
    }

    @Override
    public List<TvNotify> getTvNotifies(Channel channel) {
        return repository.findByChannel(channel);
    }

    @Override
    public TvNotify getTvNotify(Channel channel, String notifyPattern) {
        return repository.findFirstByChannelAndNotifyPattern(channel, notifyPattern);
    }

    @Override
    public TvNotify getTvNotifyById(long id) {
        Optional<TvNotify> byId = repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public int delTvNotifies(Channel channel) {
        repository.deleteAll(getTvNotifies(channel));
        return 0;
    }

    @Override
    public void delTvNotify(TvNotify notify) {
        repository.delete(notify);
    }

/*  @Override
  public List<TelkkuProgram> getChannelDailyNotifiedPrograms(Channel channel, Date day) {
    return null;
  }*/

}
