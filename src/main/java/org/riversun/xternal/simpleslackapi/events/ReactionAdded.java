package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackUser;


public interface ReactionAdded extends SlackEvent {

    String getEmojiName();
    SlackChannel getChannel();
    SlackUser getUser();
    SlackUser getItemUser();
    String getMessageID();
    String getFileID();
    String getFileCommentID();
    String getTimestamp();

}
