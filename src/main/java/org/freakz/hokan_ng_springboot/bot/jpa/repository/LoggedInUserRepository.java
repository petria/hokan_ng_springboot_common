package org.freakz.hokan_ng_springboot.bot.jpa.repository;

import org.freakz.hokan_ng_springboot.bot.jpa.entity.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Petri Airio on 13.5.2015.
 */
public interface LoggedInUserRepository extends JpaRepository<LoggedInUser, Long> {

}
