package org.riversun.xternal.simpleslackapi.replies;

import java.util.Map;

public interface EmojiSlackReply extends SlackReply {
    String getTimestamp();

    Map<String,String> getEmojis();
}
