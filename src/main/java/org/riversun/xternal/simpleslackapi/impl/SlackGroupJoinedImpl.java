package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;
import org.riversun.xternal.simpleslackapi.events.SlackGroupJoined;

class SlackGroupJoinedImpl implements SlackGroupJoined {
    private SlackChannel slackChannel;

    SlackGroupJoinedImpl(SlackChannel slackChannel)
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
        return SlackEventType.SLACK_GROUP_JOINED;
    }

}
