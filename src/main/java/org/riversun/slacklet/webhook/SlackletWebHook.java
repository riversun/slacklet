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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class SlackletWebHook {

	private final HttpServletResponse mResp;

	// Gson is thread-safe
	private final Gson mGson = new Gson();

	private Map<String, Object> mPayload;

	@SuppressWarnings("unchecked")
	SlackletWebHook(HttpServletRequest req, HttpServletResponse resp) {

		mResp = resp;

		final String payloadJson = req.getParameter("payload");
		mPayload = mGson.fromJson(payloadJson, Map.class);

	}

	public String getCallbackId() {
		return (String) mPayload.get("callback_id");
	}

	public Map<String, Object> getPayload() {
		return mPayload;
	}

	public Map<String, Object> getOriginalMessage() {
		@SuppressWarnings("unchecked")
		final Map<String, Object> originalMessage = (Map<String, Object>) mPayload.get("original_message");
		return originalMessage;
	}

	public void reply(Map<String, Object> originalMessage) {
		// Do send response
		mResp.setContentType("application/json; charset=UTF-8");
		mResp.setStatus(HttpServletResponse.SC_OK);

		// Map<String, Object> originalMessage = (Map<String, Object>)
		// payloadMap.get("original_message");

		if (originalMessage != null) {
			String originalMessageJson = mGson.toJson(originalMessage);

			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println("originalMessageJson=" + prettyGson.toJson(new JsonParser().parse(originalMessageJson)));

			PrintWriter out;
			try {
				out = mResp.getWriter();

				out.println(originalMessageJson);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
