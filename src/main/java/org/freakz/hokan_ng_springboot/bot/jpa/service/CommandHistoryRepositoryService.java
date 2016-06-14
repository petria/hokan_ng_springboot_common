package org.freakz.hokan_ng_springboot.bot.jpa.service;

import org.freakz.hokan_ng_springboot.bot.jpa.entity.CommandHistory;
import org.freakz.hokan_ng_springboot.bot.jpa.entity.CommandStatus;
import org.freakz.hokan_ng_springboot.bot.jpa.repository.CommandHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Petri Airio on 15.5.2015.
 */
@Service
public class CommandHistoryRepositoryService implements CommandHistoryService {

  @Autowired
  private CommandHistoryRepository repository;

  @Override
  public CommandHistory save(CommandHistory history) {
    return repository.save(history);
  }

  @Override
  public List<CommandHistory> findByHokanModule(String module) {
    return repository.findByHokanModule(module);
  }

  @Override
  public List<CommandHistory> findByHokanModuleAndSessionId(String module, long sessionId) {
    return repository.findByHokanModuleAndSessionId(module, sessionId);
  }

  @Override
  public List<CommandHistory> findByHokanModuleAndSessionIdAndCommandStatus(String module, long sessionId, CommandStatus status) {
    return repository.findByHokanModuleAndSessionIdAndCommandStatus(module, sessionId, status);
  }

}
