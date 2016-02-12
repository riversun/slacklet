package org.riversun.xternal.simpleslackapi.impl;

import java.util.ArrayList;

import org.riversun.xternal.simpleslackapi.SlackAttachment;
import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;
import org.riversun.xternal.simpleslackapi.events.SlackMessageUpdated;

class SlackMessageUpdatedImpl implements SlackMessageUpdated {
    private final SlackChannel channel;
    private final String       messageTimestamp;
    private final String       editionTimestamp;
    private final String       newMessage;
    private ArrayList<SlackAttachment> attachments;

    SlackMessageUpdatedImpl(SlackChannel channel, String messageTimestamp, String editionTimestamp, String newMessage) {
        this.channel = channel;
        this.messageTimestamp = messageTimestamp;
        this.editionTimestamp = editionTimestamp;
        this.newMessage = newMessage;
    }

    @Override
    public SlackChannel getChannel()
    {
        return channel;
    }

    @Override
    public String getMessageTimestamp()
    {
        return messageTimestamp;
    }

    @Override
    public String getTimeStamp()
    {
        return editionTimestamp;
    }

    @Override
    public String getNewMessage()
    {
        return newMessage;
    }

    @Override
    public SlackEventType getEventType()
    {
        return SlackEventType.SLACK_MESSAGE_UPDATED;
    }

    @Override public ArrayList<SlackAttachment> getAttachments() { return attachments; }

    public void setAttachments(ArrayList<SlackAttachment> attachments) {
        this.attachments = attachments;
    }
}
