package org.freakz.hokan_ng_springboot.bot.common.jpa.service;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.DataValues;
import org.freakz.hokan_ng_springboot.bot.common.jpa.repository.DataValuesRepository;
import org.freakz.hokan_ng_springboot.bot.common.models.DataValuesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataValuesServiceImpl implements DataValuesService {

    @Autowired
    private DataValuesRepository dataValuesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DataValuesModel> getDataValues(String channel, String network, String key) {
        List<DataValues> modelsList = dataValuesRepository.findAllByChannelAndNetworkAndKeyName(channel, network, key);
        List<DataValuesModel> models = new ArrayList<>();
        for (DataValues value : modelsList) {
            DataValuesModel model = new DataValuesModel();
            model.setNick(value.getNick());
            model.setChannel(value.getChannel());
            model.setNetwork(value.getNetwork());

            model.setKeyName(value.getKeyName());
            model.setValue(value.getValue());
            models.add(model);
        }

        return models;
    }

    @Override
    @Transactional(readOnly = true)
    public String getValue(String nick, String channel, String network, String key) {
        String value = null;
        DataValues data = dataValuesRepository.findByNickAndChannelAndNetworkAndKeyName(nick, channel, network, key);
        if (data != null) {
            value = data.getValue();
        }
        return value;
    }

    @Override
    @Transactional
    public void setValue(String nick, String channel, String network, String key, String value) {
        DataValues data = dataValuesRepository.findByNickAndChannelAndNetworkAndKeyName(nick, channel, network, key);
        if (data == null) {
            data = new DataValues();
            data.setNick(nick);
            data.setChannel(channel);
            data.setNetwork(network);
            data.setKeyName(key);
        }
        data.setValue(value);
        data = dataValuesRepository.save(data);

    }
}
