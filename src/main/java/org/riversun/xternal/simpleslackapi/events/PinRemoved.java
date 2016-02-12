package org.riversun.xternal.simpleslackapi.events;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackFile;
import org.riversun.xternal.simpleslackapi.SlackUser;

public interface PinRemoved extends SlackEvent {

    SlackUser getSender();

    SlackChannel getChannel();

    String getTimestamp();

    SlackFile getFile();

    String getMessage();

}
