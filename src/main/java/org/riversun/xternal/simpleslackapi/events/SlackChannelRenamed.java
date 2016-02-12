package org.riversun.xternal.simpleslackapi.events;

public interface SlackChannelRenamed extends SlackChannelEvent {
    String getNewName();
}
