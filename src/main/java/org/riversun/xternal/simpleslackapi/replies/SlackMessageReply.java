package org.riversun.xternal.simpleslackapi.replies;

public interface SlackMessageReply extends ParsedSlackReply {
    long getReplyTo();
    String getTimestamp();

}
