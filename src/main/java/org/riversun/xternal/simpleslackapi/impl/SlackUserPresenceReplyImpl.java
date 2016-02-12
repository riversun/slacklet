package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.replies.SlackUserPresenceReply;

public class SlackUserPresenceReplyImpl extends SlackReplyImpl implements SlackUserPresenceReply
{
    private final boolean active;

    SlackUserPresenceReplyImpl(boolean ok, String error, boolean active)
    {
        super(ok, error);
        this.active = active;
    }

    @Override
    public boolean isActive()
    {
        return active;
    }
}
