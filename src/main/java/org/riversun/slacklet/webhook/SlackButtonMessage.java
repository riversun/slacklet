/*
 * Slacklet 
 * 
 * Copyright 2016-2018 Tom Misawa, riversun.org@gmail.com
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
package org.riversun.slacklet.webhook;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.riversun.slacklet.webhook.SlackletWebHookListeners.SlackInteractiveMessageListener;
import org.riversun.xternal.simpleslackapi.SlackAttachment;

public class SlackButtonMessage {

	private final Map<String, SlackButton> mBtnList = new LinkedHashMap<String, SlackButton>();
	private String mTitle = "";
	private String mText = "";

	private String mCallbackId = null;
	private boolean mIsEnabled = false;

	public static interface OnClickListener {

		/**
		 * 
		 * @param clickedSlackButton
		 * @param originalMessage
		 * @return originalMessage edited
		 */
		public void onClick(SlackButtonMessage slackButtonMessage, SlackletWebHook webhook, SlackButton clickedSlackButton);
	}

	private OnClickListener mClickListener;

	public SlackButtonMessage setOnClickListener(OnClickListener listener) {
		mClickListener = listener;
		return SlackButtonMessage.this;

	}

	public SlackAttachment build() {

		if (mCallbackId != null) {
			throw new RuntimeException("Build has already been called.");
		}

		mIsEnabled = true;

		final String fallback = "";
		final String pretext = "";
		final SlackAttachment slackAttachment = new SlackAttachment(mTitle, fallback, mText, pretext);
		mCallbackId = "slack_interactive_message_cb_" + System.currentTimeMillis();

		slackAttachment.setCallbackId(mCallbackId);

		for (String btnName : mBtnList.keySet()) {
			final SlackButton slackBtn = mBtnList.get(btnName);
			slackAttachment.addAction(slackBtn.getAction());
		}

		final SlackletWebHookListeners webHookListeners = SlackletWebHookListeners.getInstance();

		webHookListeners.addListener(mCallbackId, new SlackInteractiveMessageListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onCallback(SlackletWebHook webHook) {

				final Map<String, Object> payload = webHook.getPayload();

				final List<Map<String, String>> actions = (List<Map<String, String>>) payload.get("actions");
				final Map<String, String> firstOfAction = actions.get(0);

				final String clickedButtonName = (String) firstOfAction.get("name");

				final SlackButton clickedSlackButton = mBtnList.get(clickedButtonName);

				if (mClickListener != null) {
					mClickListener.onClick(SlackButtonMessage.this, webHook, clickedSlackButton);
				}

			}
		});

		return slackAttachment;
	}

	public SlackButtonMessage setTitle(String title) {
		if (title != null) {
			mTitle = title;
		}
		return SlackButtonMessage.this;
	}

	public SlackButtonMessage setText(String text) {

		if (text != null) {
			mText = text;
		}

		return SlackButtonMessage.this;
	}

	public SlackButtonMessage addButton(SlackButton slackButton) {

		mBtnList.put(slackButton.name, slackButton);
		return SlackButtonMessage.this;
	}

	public void consume() {
		if (mCallbackId != null) {
			final SlackletWebHookListeners webHookListeners = SlackletWebHookListeners.getInstance();
			webHookListeners.removeListener(mCallbackId);
		}
	}

	public void setEnabled(boolean enabled) {
		mIsEnabled = enabled;
	}

	public boolean isEnabled() {
		return mIsEnabled;
	}

}
