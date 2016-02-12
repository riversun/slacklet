package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackChannel;

public interface SlackChannelEvent extends SlackEvent {
    SlackChannel getSlackChannel();
}
