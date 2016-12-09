package org.freakz.hokan_ng_springboot.bot.common.events;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Petri Airio (petri.airio@soikea.com) 08/12/2016 / 14.16
 */
public class EngineResponseTest {

    private static final String CHANNEL = "#channel";
    private static final String SENDER = "sender";

    private EngineResponseNew response;

    @Before
    public void init() {
        IrcMessageEvent event = new IrcMessageEvent();
        event.setSender(SENDER);
        event.setChannel(CHANNEL);

        this.response = new EngineResponseNew(event);
    }

    @Test
    public void testAddResponseToSender() {
        response.addResponseToSender("response");
        Assert.assertEquals(SENDER, response.getResponseMessages().get(0).getTarget());
        Assert.assertEquals("response", response.getResponseMessages().get(0).getMessage());
    }

    @Test
    public void testAddResponseToSenderChannel() {
        response.addResponseToSenderChannel("response");
        Assert.assertEquals(CHANNEL, response.getResponseMessages().get(0).getTarget());
        Assert.assertEquals("response", response.getResponseMessages().get(0).getMessage());
    }

}
