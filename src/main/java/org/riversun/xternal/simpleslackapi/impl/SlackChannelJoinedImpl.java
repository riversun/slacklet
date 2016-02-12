package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.events.SlackChannelJoined;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;

class SlackChannelJoinedImpl implements SlackChannelJoined {
    private SlackChannel slackChannel;

    SlackChannelJoinedImpl(SlackChannel slackChannel)
    {
        this.slackChannel = slackChannel;
    }

    @Override
    public SlackChannel getSlackChannel()
    {
        return slackChannel;
    }

    void setSlackChannel(SlackChannel slackChannel)
    {
        this.slackChannel = slackChannel;
    }

    @Override
    public SlackEventType getEventType()
    {
        return SlackEventType.SLACK_CHANNEL_JOINED;
    }

}
