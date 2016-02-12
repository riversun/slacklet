package org.riversun.xternal.simpleslackapi.impl;

import org.riversun.xternal.simpleslackapi.replies.GenericSlackReply;

class GenericSlackReplyImpl implements GenericSlackReply
{
    private String obj;

    public GenericSlackReplyImpl(String obj)
    {
        this.obj = obj;
    }

    @Override
    public String getPlainAnswer()
    {
        return obj;
    }

}
