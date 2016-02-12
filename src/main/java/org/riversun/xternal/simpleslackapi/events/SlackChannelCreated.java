package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackUser;

public interface SlackChannelCreated extends SlackChannelEvent {
    SlackUser getCreator();
}
