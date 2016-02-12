package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackPersona;
import org.riversun.xternal.simpleslackapi.events.SlackConnected;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;

class SlackConnectedImpl implements SlackConnected
{
    private SlackPersona slackConnectedPersona;

    SlackConnectedImpl(SlackPersona slackConnectedPersona)
    {
        this.slackConnectedPersona = slackConnectedPersona;
    }

    @Override
    public SlackPersona getConnectedPersona()
    {
        return slackConnectedPersona;
    }

    @Override
    public SlackEventType getEventType()
    {
        return SlackEventType.SLACK_CONNECTED;
    }
}
