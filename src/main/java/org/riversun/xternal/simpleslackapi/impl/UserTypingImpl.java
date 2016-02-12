package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackUser;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;
import org.riversun.xternal.simpleslackapi.events.UserTyping;

public class UserTypingImpl implements UserTyping {
  private final SlackChannel slackChannel;
  private final SlackUser slackUser;
  private final SlackEventType slackEventType;

  public UserTypingImpl(SlackChannel slackChannel, SlackUser slackUser, SlackEventType slackEventType) {
    this.slackChannel = slackChannel;
    this.slackUser = slackUser;
    this.slackEventType = slackEventType;
  }

  @Override
  public SlackChannel getChannel() {
    return slackChannel;
  }

  @Override
  public SlackUser getUser() {
    return slackUser;
  }

  @Override
  public SlackEventType getEventType() {
    return slackEventType;
  }
}
