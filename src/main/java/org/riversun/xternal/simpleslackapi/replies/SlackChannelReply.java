package org.riversun.xternal.simpleslackapi.replies;

import org.riversun.xternal.simpleslackapi.SlackChannel;

public interface SlackChannelReply extends ParsedSlackReply {
    SlackChannel getSlackChannel();
}
