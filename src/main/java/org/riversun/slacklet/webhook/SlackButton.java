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

import org.riversun.xternal.simpleslackapi.SlackAction;

public class SlackButton {

	private SlackAction mAction;

	public final String name;
	public final String label;
	public final String value;

	public SlackButton(String name, String label, String value) {
		mAction = new SlackAction(name, label, SlackAction.TYPE_BUTTON, value);
		this.name = name;
		this.label = label;
		this.value = value;
	}

	public SlackAction getAction() {
		return mAction;
	}

	@Override
	public String toString() {
		return "SlackButton [name=" + name + ", label=" + label + ", value=" + value + "]";
	}

}
