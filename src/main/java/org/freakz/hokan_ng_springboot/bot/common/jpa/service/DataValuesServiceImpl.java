package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.DataValues;
import org.freakz.hokan_ng_springboot.bot.common.jpa.repository.DataValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class DataValuesServiceImpl implements DataValuesService {

    @Autowired
    private DataValuesRepository dataValuesRepository;

    @Override
    @Transactional(readOnly = true)
    public String getValue(String nick, String network, String key) {
        String value = null;
        DataValues data = dataValuesRepository.findByNickAndNetworkAndKeyName(nick, network, key);
        if (data != null) {
            value = data.getValue();
        }
        return value;
    }

    @Override
    @Transactional
    public void setValue(String nick, String network, String key, String value) {
        DataValues data = dataValuesRepository.findByNickAndNetworkAndKeyName(nick, network, key);
        if (data == null) {
            data = new DataValues();
            data.setNick(nick);
            data.setNetwork(network);
            data.setKeyName(key);
        }
        data.setValue(value);
        data = dataValuesRepository.save(data);

    }
}
