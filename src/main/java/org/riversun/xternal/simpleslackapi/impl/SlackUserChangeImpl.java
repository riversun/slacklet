package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackUser;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;
import org.riversun.xternal.simpleslackapi.events.SlackUserChange;

public class SlackUserChangeImpl implements SlackUserChange {

    private final SlackUser slackUser;

    SlackUserChangeImpl(SlackUser slackUser) {
        this.slackUser = slackUser;
    }

    @Override
    public SlackUser getUser() {
        return slackUser;
    }

    @Override
    public SlackEventType getEventType() {
        return SlackEventType.SLACK_USER_CHANGE;
    }
}
