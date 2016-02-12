package org.riversun.xternal.simpleslackapi.events;

public interface SlackMessageEvent extends SlackEvent {
    String getTimeStamp();
}
