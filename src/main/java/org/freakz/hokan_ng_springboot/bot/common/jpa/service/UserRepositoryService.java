package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.User;
import org.freakz.hokan_ng_springboot.bot.common.jpa.repository.UserRepository;
import org.freakz.hokan_ng_springboot.bot.common.util.StringStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Petri Airio on 11.3.2015.
 */
@Service
public class UserRepositoryService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findFirstByNick(String nick) {
        return userRepository.findFirstByNick(nick);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByMask(String mask) {
        List<User> users = findAll();
        for (User user : users) {
            if (StringStuff.match(mask, user.getMask())) {
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void setAllLoggedIn(int value) {
        List<User> all = findAll();
        for (User user : all) {
            user.setLoggedIn(value);
        }
        userRepository.save(all);
    }

}
