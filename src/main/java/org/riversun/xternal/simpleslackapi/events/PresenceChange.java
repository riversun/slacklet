package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackPersona.SlackPresence;

public interface PresenceChange extends SlackEvent {

    String getUserId();
    SlackPresence getPresence();
}
