package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.events.SlackChannelRenamed;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;

class SlackChannelRenamedImpl implements SlackChannelRenamed
{
    private SlackChannel slackChannel;
    private String       newName;

    SlackChannelRenamedImpl(SlackChannel slackChannel, String newName)
    {
        this.slackChannel = slackChannel;
        this.newName = newName;
    }

    @Override
    public SlackChannel getSlackChannel()
    {
        return slackChannel;
    }

    @Override
    public String getNewName()
    {
        return newName;
    }

    @Override
    public SlackEventType getEventType()
    {
        return SlackEventType.SLACK_CHANNEL_RENAMED;
    }

}
