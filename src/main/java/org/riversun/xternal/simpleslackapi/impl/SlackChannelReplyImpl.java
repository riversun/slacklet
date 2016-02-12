package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.replies.SlackChannelReply;

public class SlackChannelReplyImpl extends SlackReplyImpl implements SlackChannelReply
{
    private SlackChannel slackChannel;

    SlackChannelReplyImpl(boolean ok, String error, SlackChannel slackChannel)
    {
        super(ok,error);
        this.slackChannel = slackChannel;
    }

    @Override
    public SlackChannel getSlackChannel()
    {
        return slackChannel;
    }
}
