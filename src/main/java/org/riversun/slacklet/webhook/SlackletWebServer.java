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

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SlackletWebServer {

	private final Server mServer = new Server();

	public void startServer(String path, int port) {

		org.eclipse.jetty.util.log.Log.setLog(new SlackNoLogger());

		final int PORT = port;

		ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

		servletHandler.addServlet(new ServletHolder(SlackletWebHookHandler.class), path);

		final ResourceHandler resourceHandler = new ResourceHandler();

		resourceHandler.setDirectoriesListed(false);
		resourceHandler.setCacheControl("no-store,no-cache,must-revalidate");

		HandlerList handlerList = new HandlerList();

		handlerList.addHandler(resourceHandler);
		handlerList.addHandler(servletHandler);

		mServer.setHandler(handlerList);

		final HttpConfiguration httpConfig = new HttpConfiguration();

		httpConfig.setSendServerVersion(false);
		final HttpConnectionFactory httpConnFactory = new HttpConnectionFactory(httpConfig);
		final ServerConnector httpConnector = new ServerConnector(mServer, httpConnFactory);
		httpConnector.setPort(PORT);
		mServer.setConnectors(new Connector[] { httpConnector });

		try {
			mServer.start();
			mServer.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		try {
			if (mServer.isStarted()) {
				mServer.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}