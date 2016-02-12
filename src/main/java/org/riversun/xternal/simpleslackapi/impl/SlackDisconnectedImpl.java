package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackPersona;
import org.riversun.xternal.simpleslackapi.events.SlackDisconnected;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;

public class SlackDisconnectedImpl implements SlackDisconnected {
    private SlackPersona persona;

    public SlackDisconnectedImpl(SlackPersona persona) {
        this.persona = persona;
    }

    @Override
    public SlackPersona getDisconnectedPersona() {
        return persona;
    }

    @Override
    public SlackEventType getEventType() {
        return SlackEventType.SLACK_DISCONNECTED;
    }
}
