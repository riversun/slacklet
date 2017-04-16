/*
 * Slacklet 
 * 
 * Copyright 2016 Tom Misawa, riversun.org@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
 * Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package org.riversun.slacklet;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackSession;
import org.riversun.xternal.simpleslackapi.SlackUser;
import org.riversun.xternal.simpleslackapi.events.SlackEventType;
import org.riversun.xternal.simpleslackapi.events.SlackMessagePosted;
import org.riversun.xternal.simpleslackapi.listeners.SlackMessagePostedListener;

/**
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
class SletListenerMessagePosted implements SlackMessagePostedListener {

	private SlackletService mService;

	public SletListenerMessagePosted(SlackletService service) {
		mService = service;
	}

	@Override
	public void onEvent(SlackMessagePosted msg, SlackSession slackSession) {

		final SlackUser senderUser = msg.getSender();
		final SlackUser bot = mService.getBot();

		if (bot.getId().equals(senderUser.getId())) {
			// if message from this bot.
			return;
		}

		final SlackChannel channel = msg.getChannel();

		final boolean isDirectMessageReceived = channel.getId().startsWith("D");

		// make sure this event is SLACK_MESSAGE_POSTED.
		if (msg.getEventType() == SlackEventType.SLACK_MESSAGE_POSTED) {

			final SlackletSession userSession = mService.getUserSlackletSession(msg);

			final SlackletRequest req = new SlackletRequest(mService, msg, userSession);
			final SlackletResponse resp = new SlackletResponse(mService, msg);

			if (isDirectMessageReceived) {

				onDirectMessagePosted(req, resp);

			} else {

				final String content = msg.getMessageContent();
				final String userDisp = "<@" + bot.getId() + ">";

				boolean isMentioned = content.contains(userDisp);

				if (isMentioned) {
					onMentionedMessagePosted(req, resp);
				} else {
					onMessagePosted(req, resp);
				}
			}

		}

	}

	private void onDirectMessagePosted(final SlackletRequest req, final SlackletResponse resp) {
		final ExecutorService executor = mService.getExecutor();
		final List<Slacklet> slackletList = mService.getSlackletList();

		for (final Slacklet slet : slackletList) {
			final Runnable r = new Runnable() {
				@Override
				public void run() {
					slet.onDirectMessagePosted(req, resp);
				}
			};
			executor.submit(r);

		}
	}

	private void onMentionedMessagePosted(final SlackletRequest req, final SlackletResponse resp) {

		final ExecutorService executor = mService.getExecutor();
		final List<Slacklet> slackletList = mService.getSlackletList();

		for (final Slacklet slet : slackletList) {
			final Runnable r = new Runnable() {
				@Override
				public void run() {
					slet.onMentionedMessagePosted(req, resp);
				}
			};
			executor.submit(r);

		}
	}

	private void onMessagePosted(final SlackletRequest req, final SlackletResponse resp) {
		final ExecutorService executor = mService.getExecutor();
		final List<Slacklet> slackletList = mService.getSlackletList();

		for (final Slacklet slet : slackletList) {
			final Runnable r = new Runnable() {
				@Override
				public void run() {
					slet.onMessagePosted(req, resp);
				}
			};
			executor.submit(r);

		}
	}

}
