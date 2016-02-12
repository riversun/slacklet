package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackPersona;

public interface SlackDisconnected extends SlackEvent {

    SlackPersona getDisconnectedPersona();
}
