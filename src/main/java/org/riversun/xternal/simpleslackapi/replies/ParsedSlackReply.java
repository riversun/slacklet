package org.riversun.xternal.simpleslackapi.replies;

public interface ParsedSlackReply extends SlackReply {
    boolean isOk();
    String getErrorMessage();
}
