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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlackletWebHookListeners {

	public static final SlackletWebHookListeners instance = new SlackletWebHookListeners();

	public interface SlackInteractiveMessageListener {
		/**
		 * Returns if webhook is consumed.
		 * 
		 * @param webHook
		 * @return
		 */
		public void onCallback(SlackletWebHook webHook);
	}

	public final Map<String, SlackInteractiveMessageListener> mListeners = new ConcurrentHashMap<String, SlackInteractiveMessageListener>();

	private SlackletWebHookListeners() {
	}

	void addListener(String callbackId, SlackInteractiveMessageListener listener) {
		mListeners.put(callbackId, listener);
	}

	void removeListener(String listenerId) {
		if (mListeners.containsKey(listenerId)) {
			mListeners.remove(listenerId);
		}
	}

	Map<String, SlackInteractiveMessageListener> getListeners() {
		return mListeners;
	}

	void onCallback(SlackletWebHook webHook) {

		final String callbackId = webHook.getCallbackId();

		final SlackInteractiveMessageListener listener = mListeners.get(callbackId);

		if (listener != null) {

			listener.onCallback(webHook);

		} else {

		}

	}

	public static SlackletWebHookListeners getInstance() {
		return instance;
	}
}
