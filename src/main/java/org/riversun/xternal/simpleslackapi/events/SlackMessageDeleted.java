package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackChannel;

public interface SlackMessageDeleted extends SlackMessageEvent {
    SlackChannel getChannel();
    String getMessageTimestamp();
}
