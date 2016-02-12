package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackUser;

public interface SlackChannelUnarchived extends SlackChannelEvent {
    SlackUser getUser();
}
