package org.freakz.hokan_ng_springboot.bot.common.events;

import java.io.Serializable;

/**
 * Created by Petri Airio (petri.airio@soikea.com) 08/12/2016 / 14.24
 */
public class IrcMessage implements Serializable {


    private String target;
    private String message;


    public IrcMessage(String target, String message) {
        this.target = target;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {

        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
