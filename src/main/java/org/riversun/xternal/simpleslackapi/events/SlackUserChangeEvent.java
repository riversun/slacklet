package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackUser;

public interface SlackUserChangeEvent extends SlackEvent {

    SlackUser getUser();
}
