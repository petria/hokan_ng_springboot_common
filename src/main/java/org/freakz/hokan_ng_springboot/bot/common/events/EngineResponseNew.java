package org.freakz.hokan_ng_springboot.bot.common.events;

import org.freakz.hokan_ng_springboot.bot.common.exception.HokanEngineException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petri Airio (petri.j.airio@gmail.com) 08/12/2016 / 14.17
 */
public class EngineResponseNew implements EngineResponse, Serializable {

    private final IrcMessageEvent ircMessageEvent;
    private final List<IrcMessage> responseMessages;

    public EngineResponseNew(IrcMessageEvent ircMessageEvent) {

        this.ircMessageEvent = ircMessageEvent;
        this.responseMessages = new ArrayList<>();
    }

    public void addResponseToSender(String response) {
        String target = ircMessageEvent.getSender();
        IrcMessage ircMessage = new IrcMessage(target, response);
        responseMessages.add(ircMessage);
    }

    public String getSender() {
        return ircMessageEvent.getSender();
    }

    @Override
    public List<IrcMessage> getResponseMessages() {
        return this.responseMessages;
    }

    public void addResponseToSenderChannel(String response) {
        String target = ircMessageEvent.getChannel();
        IrcMessage ircMessage = new IrcMessage(target, response);
        responseMessages.add(ircMessage);
    }

    @Override
    public void setResponseMessage(String message) {

    }

    @Override
    public void setException(HokanEngineException engineException) {

    }

    @Override
    public boolean isEngineRequest() {
        return false;
    }

    @Override
    public void setIsEngineRequest(boolean isEngineRequest) {

    }

    @Override
    public void setReplyTo(String name) {

    }

    @Override
    public void addResponse(String response, Object... args) {

    }

    @Override
    public void addResponse(StringBuilder sb, Object... args) {

    }

    @Override
    public String getResponseMessage() {
        String response = "";
        for (IrcMessage ircMessage : responseMessages) {
            response += ircMessage.getMessage();
            if (!response.endsWith("\n")) {
                response += "\n";
            }
        }
        return response;
    }

    @Override
    public IrcMessageEvent getIrcMessageEvent() {
        return null;
    }

    @Override
    public void addEngineMethodCall(String methodName, String... methodArgs) {

    }

    @Override
    public boolean isNoSearchReplace() {
        return false;
    }

    @Override
    public void setNoSearchReplace(boolean noSR) {

    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public String getReplyTo() {
        return null;
    }
}
