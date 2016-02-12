package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackUser;

public interface SlackChannelArchived extends SlackChannelEvent {
    SlackUser getUser();
}
