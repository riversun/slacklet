package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackPersona;

public interface SlackConnected extends SlackEvent {
    SlackPersona getConnectedPersona();
}
