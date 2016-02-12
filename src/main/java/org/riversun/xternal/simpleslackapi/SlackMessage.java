package org.riversun.xternal.simpleslackapi;

import org.riversun.xternal.simpleslackapi.events.SlackMessageEvent;
import org.riversun.xternal.simpleslackapi.events.SlackMessagePosted;

/**
 * 
 * @deprecated use {@link SlackMessagePosted}
 *
 */
@Deprecated
public interface SlackMessage extends SlackMessageEvent {

    String getMessageContent();

    SlackUser getSender();

    SlackBot getBot();

    SlackChannel getChannel();

}
