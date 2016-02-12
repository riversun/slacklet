package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.ChannelHistoryModule;
import org.riversun.xternal.simpleslackapi.SlackSession;

public class ChannelHistoryModuleFactory {
    
    public static ChannelHistoryModule createChannelHistoryModule(SlackSession session){
        return new ChannelHistoryModuleImpl(session);
    };
    
}
