package org.riversun.xternal.simpleslackapi.listeners;

import org.riversun.xternal.simpleslackapi.SlackSession;
import org.riversun.xternal.simpleslackapi.events.SlackEvent;

public interface SlackEventListener<T extends SlackEvent> {
    void onEvent(T event, SlackSession session);
}
