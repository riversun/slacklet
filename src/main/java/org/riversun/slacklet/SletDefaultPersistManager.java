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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * On memory session persistence manager.<br>
 * Forget session data when you restart slacklet service.
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class SletDefaultPersistManager implements SletPersistManager {

	private final Map<String, Map<String, Object>> mMap = new ConcurrentHashMap<>();

	@Override
	public synchronized void saveAttribute(String userId, String key, Object value) {
		Map<String, Object> userMap = mMap.get(userId);

		if (userMap == null) {
			userMap = new HashMap<>();
			mMap.put(userId, userMap);
		}

		userMap.put(key, value);
	}

	@Override
	public synchronized Object loadAttribute(String userId, String key) {

		Map<String, Object> userMap = mMap.get(userId);

		if (userMap == null) {
			userMap = new HashMap<>();
			mMap.put(userId, userMap);
		}

		return userMap.get(key);
	}

}
