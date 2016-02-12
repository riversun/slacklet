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

import org.riversun.xternal.simpleslackapi.SlackUser;

/**
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class SlackletSession {

	private SlackUser mSlackUser;

	private SletPersistManager mSessionPersistenceManager;

	public SlackletSession(SlackUser slackUser, SletPersistManager sessionPersistenceManager) {
		mSlackUser = slackUser;
		mSessionPersistenceManager = sessionPersistenceManager;
	}

	public SlackUser getUser() {
		return mSlackUser;
	}

	/**
	 * Returns user mention text
	 * 
	 * @return
	 */
	public String getUserDisp() {
		return "<@" + mSlackUser.getId() + ">";
	}

	/**
	 * Set attribute
	 * 
	 * @param key
	 * @param value
	 */
	public synchronized void setAttribute(String key, Object value) {
		saveAttribute(mSlackUser.getId(), key, value);
	}

	/**
	 * Get attribute
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public synchronized Object getAttribute(String key, Object defaultValue) {

		Object value = getAttribute(key);

		if (value == null) {
			value = defaultValue;

			if (value != null) {
				setAttribute(key, value);
			}
		}
		return value;
	}

	public synchronized Object getAttribute(String key) {
		return loadAttribute(mSlackUser.getId(), key);
	}

	void saveAttribute(String userId, String key, Object value) {
		mSessionPersistenceManager.saveAttribute(userId, key, value);

	}

	Object loadAttribute(String userId, String key) {
		return mSessionPersistenceManager.loadAttribute(userId, key);
	}

}