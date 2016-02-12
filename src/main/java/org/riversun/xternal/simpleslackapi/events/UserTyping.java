package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackUser;

public interface UserTyping extends SlackEvent {
    SlackChannel getChannel();
    SlackUser getUser();
}
