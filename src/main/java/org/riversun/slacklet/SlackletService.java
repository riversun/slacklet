package org.riversun.slacklet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.riversun.slacklet.webhook.SlackletWebServer;
import org.riversun.xternal.simpleslackapi.ChannelHistoryModule;
import org.riversun.xternal.simpleslackapi.SlackAttachment;
import org.riversun.xternal.simpleslackapi.SlackChannel;
import org.riversun.xternal.simpleslackapi.SlackMessageHandle;
import org.riversun.xternal.simpleslackapi.SlackPersona;
import org.riversun.xternal.simpleslackapi.SlackSession;
import org.riversun.xternal.simpleslackapi.SlackUser;
import org.riversun.xternal.simpleslackapi.events.PinAdded;
import org.riversun.xternal.simpleslackapi.events.PinRemoved;
import org.riversun.xternal.simpleslackapi.events.ReactionAdded;
import org.riversun.xternal.simpleslackapi.events.ReactionRemoved;
import org.riversun.xternal.simpleslackapi.events.SlackChannelArchived;
import org.riversun.xternal.simpleslackapi.events.SlackChannelCreated;
import org.riversun.xternal.simpleslackapi.events.SlackChannelJoined;
import org.riversun.xternal.simpleslackapi.events.SlackChannelLeft;
import org.riversun.xternal.simpleslackapi.events.SlackChannelRenamed;
import org.riversun.xternal.simpleslackapi.events.SlackChannelUnarchived;
import org.riversun.xternal.simpleslackapi.events.SlackGroupJoined;
import org.riversun.xternal.simpleslackapi.events.SlackMessageDeleted;
import org.riversun.xternal.simpleslackapi.events.SlackMessagePosted;
import org.riversun.xternal.simpleslackapi.events.SlackMessageUpdated;
import org.riversun.xternal.simpleslackapi.events.SlackUserChange;
import org.riversun.xternal.simpleslackapi.impl.ChannelHistoryModuleFactory;
import org.riversun.xternal.simpleslackapi.impl.SlackSessionFactory;
import org.riversun.xternal.simpleslackapi.listeners.PinAddedListener;
import org.riversun.xternal.simpleslackapi.listeners.PinRemovedListener;
import org.riversun.xternal.simpleslackapi.listeners.ReactionAddedListener;
import org.riversun.xternal.simpleslackapi.listeners.ReactionRemovedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelArchivedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelCreatedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelJoinedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelLeftListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelRenamedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackChannelUnarchivedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackGroupJoinedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackMessageDeletedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackMessageUpdatedListener;
import org.riversun.xternal.simpleslackapi.listeners.SlackUserChangeListener;
import org.riversun.xternal.simpleslackapi.replies.SlackMessageReply;

/**
 * SlackletService
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class SlackletService {

	private final String mBotToken;

	private SlackSession mBotSession;

	private SlackUser mBot;

	private Map<String, SlackletSession> mUserSessions = new ConcurrentHashMap<String, SlackletSession>();

	private Map<String, SlackChannel> mDirectMessageChannelMap = null;

	private final List<Slacklet> mSlackletList = new ArrayList<Slacklet>();

	private final ExecutorService mExecutor = Executors.newCachedThreadPool(); // newFixedThreadPool(20);

	private SletPersistManager mSessionPersistenceManager = new SletDefaultPersistManager();

	private SlackletWebServer mWebServer;

	/**
	 * 
	 * @param apiToken
	 *            API Token that you can get from here.
	 *            https://my.slack.com/services/new/bot <br>
	 *            This is bot token.<br>
	 *            Not user token.
	 */
	public SlackletService(String apiToken) {
		mBotToken = apiToken;
	}

	/**
	 * Start slacklet service
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {

		final SlackSession botSession = SlackSessionFactory.createWebSocketSlackSession(mBotToken);
		botSession.connect();

		mBotSession = botSession;

		mBot = getBotUser();

		initialilze();
	}

	/**
	 * Start slacklet service with webhook support
	 * 
	 * @param webhookPath
	 * @param port
	 * @throws IOException
	 */
	public void startWithWebHook(String webhookPath, int port) throws IOException {

		this.start();
		if (mWebServer == null) {
			mWebServer = new SlackletWebServer();

		} else {
			mWebServer.stopServer();
		}
		mWebServer.startServer(webhookPath, port);
	}

	public void stop() throws IOException {
		mBotSession.disconnect();
		if (mWebServer != null) {
			mWebServer.stopServer();
			mWebServer = null;
		}
	}

	public void startWebhook(String webhookPath, int port) {

	}

	public void addSlacklet(Slacklet slackWrap) {
		if (slackWrap != null) {
			mSlackletList.add(slackWrap);
		}
	}

	private void initialilze() {

		mBotSession.addChannelArchivedListener(new SlackChannelArchivedListener() {

			@Override
			public void onEvent(SlackChannelArchived event, SlackSession session) {
				// TODO handle event
			}
		});
		mBotSession.addChannelCreatedListener(new SlackChannelCreatedListener() {

			@Override
			public void onEvent(SlackChannelCreated event, SlackSession session) {
				// TODO handle event
			}
		});
		mBotSession.addChannelUnarchivedListener(new SlackChannelUnarchivedListener() {

			@Override
			public void onEvent(SlackChannelUnarchived event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addChannelJoinedListener(new SlackChannelJoinedListener() {

			@Override
			public void onEvent(SlackChannelJoined event, SlackSession session) {
				// TODO handle event
			}
		});
		mBotSession.addChannelLeftListener(new SlackChannelLeftListener() {

			@Override
			public void onEvent(SlackChannelLeft event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addChannelRenamedListener(new SlackChannelRenamedListener() {

			@Override
			public void onEvent(SlackChannelRenamed event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addGroupJoinedListener(new SlackGroupJoinedListener() {

			@Override
			public void onEvent(SlackGroupJoined event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addMessageDeletedListener(new SlackMessageDeletedListener() {

			@Override
			public void onEvent(SlackMessageDeleted event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addMessageUpdatedListener(new SlackMessageUpdatedListener() {

			@Override
			public void onEvent(SlackMessageUpdated event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addMessagePostedListener(new SletListenerMessagePosted(SlackletService.this));

		mBotSession.addPinAddedListener(new PinAddedListener() {

			@Override
			public void onEvent(PinAdded event, SlackSession session) {
				// TODO handle event
			}
		});
		mBotSession.addPinRemovedListener(new PinRemovedListener() {

			@Override
			public void onEvent(PinRemoved event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addReactionAddedListener(new ReactionAddedListener() {

			@Override
			public void onEvent(ReactionAdded event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addReactionRemovedListener(new ReactionRemovedListener() {

			@Override
			public void onEvent(ReactionRemoved event, SlackSession session) {
				// TODO handle event
			}
		});

		mBotSession.addSlackUserChangeListener(new SlackUserChangeListener() {

			@Override
			public void onEvent(SlackUserChange event, SlackSession session) {
				// TODO handle event
			}
		});
	}

	List<Slacklet> getSlackletList() {
		return mSlackletList;
	}

	ExecutorService getExecutor() {
		return mExecutor;
	}

	public boolean sendMessageTo(String channelName, String message) {
		final SlackChannel channel = mBotSession.findChannelByName(channelName);
		if (channel != null) {
			sendMessageTo(channel, message);
			return true;
		}
		return false;
	}

	public boolean sendMessageTo(String channelName, String message, SlackAttachment attch) {
		final SlackChannel channel = mBotSession.findChannelByName(channelName);
		if (channel != null) {
			sendMessageTo(channel, message, attch);
			return true;
		}
		return false;
	}

	public SlackMessageHandle<SlackMessageReply> sendMessageTo(SlackChannel channel, String message, SlackAttachment attch) {

		SlackMessageHandle<SlackMessageReply> sendMessage = getSlackSession().sendMessage(channel, message, attch);
		return sendMessage;

	}

	public List<SlackMessagePosted> getHistory(SlackUser user) {

		final SlackChannel dmChannel = getDirectMessageChannel(user);

		ChannelHistoryModule channelHistoryModule = ChannelHistoryModuleFactory.createChannelHistoryModule(getSlackSession());

		List<SlackMessagePosted> messages = channelHistoryModule.fetchHistoryOfChannel(dmChannel.getId());
		return messages;
	}

	public SlackMessageHandle<SlackMessageReply> deleteMessage(String timeStamp, SlackChannel channel) {
		SlackMessageHandle<SlackMessageReply> deleteMessage = getSlackSession().deleteMessage(timeStamp, channel);
		return deleteMessage;
	}

	public SlackMessageHandle<SlackMessageReply> sendMessageTo(SlackChannel channel, String message) {

		SlackMessageHandle<SlackMessageReply> sendMessage = getSlackSession().sendMessage(channel, message);
		return sendMessage;

	}

	public boolean sendDirectMessageTo(String userName, String message, SlackAttachment attch) {
		final SlackUser user = mBotSession.findUserByUserName(userName);
		if (user != null) {
			sendDirectMessageTo(user, message, attch);
			return true;
		}
		return false;

	}

	public boolean sendDirectMessageTo(String userName, String message) {
		final SlackUser user = mBotSession.findUserByUserName(userName);
		if (user != null) {
			sendDirectMessageTo(user, message, null);
			return true;
		}
		return false;

	}

	public SlackMessageHandle<SlackMessageReply> sendDirectMessageTo(SlackUser user, String message) {

		final SlackChannel dmChannel = getDirectMessageChannel(user);

		if (dmChannel != null) {
			SlackMessageHandle<SlackMessageReply> sendMessageTo = sendMessageTo(dmChannel, message);
			return sendMessageTo;
		}
		return null;
	}

	public SlackMessageHandle<SlackMessageReply> sendDirectMessageTo(SlackUser user, String message, SlackAttachment attch) {

		final SlackChannel dmChannel = getDirectMessageChannel(user);

		if (dmChannel != null) {
			SlackMessageHandle<SlackMessageReply> sendMessageTo = sendMessageTo(dmChannel, message, attch);
			return sendMessageTo;
		}
		return null;
	}

	SlackletSession getUserSlackletSession(SlackMessagePosted msg) {

		final SlackUser sender = msg.getSender();
		final String userId = sender.getId();

		SlackletSession slackWrapSession = mUserSessions.get(userId);
		if (slackWrapSession == null) {
			slackWrapSession = new SlackletSession(sender, mSessionPersistenceManager);
			mUserSessions.put(userId, slackWrapSession);
		}
		return slackWrapSession;
	}

	public void setSessionPersistenceManager(SletPersistManager sessionPersistenceManager) {
		mSessionPersistenceManager = sessionPersistenceManager;
	}

	public SlackUser getBot() {
		return mBot;
	}

	public SlackSession getSlackSession() {
		return mBotSession;
	}

	private SlackUser getBotUser() {

		final SlackPersona persona = mBotSession.sessionPersona();

		final Collection<SlackUser> users = mBotSession.getUsers();

		SlackUser botUser = null;

		for (SlackUser slackUser : users) {

			if (persona.getId().equals(slackUser.getId())) {

				botUser = slackUser;

				break;
			}
		}

		return botUser;
	}

	public SlackChannel getDirectMessageChannel(SlackUser user) {

		if (mDirectMessageChannelMap == null) {
			mDirectMessageChannelMap = new HashMap<String, SlackChannel>();
			updateDirectMessageChannel();
		}

		final SlackChannel dmChannel = mDirectMessageChannelMap.get(user.getId());

		return dmChannel;

	}

	private void updateDirectMessageChannel() {

		mDirectMessageChannelMap.clear();

		final Collection<SlackChannel> channels = mBotSession.getChannels();

		for (SlackChannel channel : channels) {

			Collection<SlackUser> members = channel.getMembers();

			if (channel.getId().startsWith("D")) {

				for (SlackUser member : members) {
					mDirectMessageChannelMap.put(member.getId(), channel);
					break;
				}
			}

		}

	}

}
