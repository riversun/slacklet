package org.riversun.xternal.simpleslackapi.events;

import java.util.ArrayList;

import org.riversun.xternal.simpleslackapi.SlackAttachment;
import org.riversun.xternal.simpleslackapi.SlackChannel;

public interface SlackMessageUpdated extends SlackMessageEvent {
    SlackChannel getChannel();
    String getMessageTimestamp();
    String getNewMessage();
    ArrayList<SlackAttachment> getAttachments();
}
