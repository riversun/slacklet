package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.SlackBot;

@Deprecated
class SlackBotImpl extends SlackPersonaImpl implements SlackBot
{
    SlackBotImpl(String id, String userName, String realName, String userMail, String userSkype, String userPhone, String userTitle,
                 boolean deleted, boolean admin, boolean owner, boolean primaryOwner, boolean restricted, boolean ultraRestricted, SlackPresence presence)
    {
        super(id, userName, realName, userMail, userSkype, userPhone, userTitle, deleted, admin, owner, primaryOwner, restricted, ultraRestricted, true,null,null,0, presence);
    }

}
