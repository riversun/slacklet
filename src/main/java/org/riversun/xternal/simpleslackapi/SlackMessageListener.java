package org.riversun.xternal.simpleslackapi;

@Deprecated
public interface SlackMessageListener {
    void onSessionLoad(SlackSession session);
    void onMessage(SlackMessage message);
}
