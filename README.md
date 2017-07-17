# Overview
**Slacklet**  is a easy slack bot library based on [simple slack api](https://github.com/Ullink/simple-slack-api).  
You can make your interactive Slack Bot easily and rapidly.

It is licensed under [MIT](https://opensource.org/licenses/MIT).

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.riversun/slacklet/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.riversun/slacklet)

# Get your bot token from Slack

First of all,get your bot token from here.  
https://my.slack.com/services/new/bot

# Quick Examples

##  1.Reply to user posted message 

```java
public class Example00 {

	public static void main(String[] args) throws IOException {

		String botToken = "[YOUR_BOT_TOKEN]";

		SlackletService slackService = new SlackletService(botToken);

		slackService.addSlacklet(new Slacklet() {

			@Override
			public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
				// user posted message and BOT intercepted it

				// get message content
				String content = req.getContent();

				// reply to the user
				resp.reply("You say '" + content + "'.");
			}
		});

		slackService.start();

	}

}

```

### How to Run

1. First, Invite your bot to #random channel.  
<img src="https://riversun.github.io/slacklet/img/ex00_00_join.png"/>

2. Run Example00

3. You type 'Hi!' and bot replies this.  
<img src="https://riversun.github.io/slacklet/img/ex00_01.png"/>

In the example above, it responds to messages that came to all channels the BOT is joining.  
If you want to reply only to messages that came to the **random** channel, change as follows.

```java
@Override
public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
	//  user posted message and BOT intercepted it

	SlackChannel channel = req.getChannel();

	if ("random".equals(channel.getName())) {

		// get message content
		String content = req.getContent();

		// reply to the user
		resp.reply("You say '" + content + "'.");

	}

}
```

<hr>

##  2.Receive direct Message and mentioned Message 

```java

public class Example01 {

	public static void main(String[] args) throws IOException {

		String botToken ="[YOUR_BOT_TOKEN]";

		SlackletService slackService = new SlackletService(botToken);

		slackService.addSlacklet(new Slacklet() {

			@Override
			public void onDirectMessagePosted(SlackletRequest req, SlackletResponse resp) {
				// BOT received direct message from user

				// get message content
				String content = req.getContent();

				// reply to the user
				resp.reply("You say '" + content + "'.");
			}

			@Override
			public void onMentionedMessagePosted(SlackletRequest req, SlackletResponse resp) {
				// BOT received message mentioned to the BOT such like "@bot How are you?"
				// from user.

				String content = req.getContent();

				// get 'mention' text formatted <@user> of sender user.
				String mention = req.getUserDisp();
				resp.reply("Hi," + mention + ". You say '" + content + "'.");
			}

		});

		slackService.start();

	}

}
```

### How to Run

1. Run **Example01**  
2. You type 'Hello' as a direct message to bot.    
3. **onDirectMessagePosted** is called and it replies like this.  
<img src="https://riversun.github.io/slacklet/img/ex01_00.png"/>

4. You type 'How r u' on the **random** channel.  
5. **onMentionedMessagePosted** is called and it replies like this.  
<img src="https://riversun.github.io/slacklet/img/ex01_01_mentioned.png"/>

<hr>

##  3.Sending Direct Message to user 

```java
public class Example02 {

	public static void main(String[] args) throws IOException {

		String botToken = "[YOUR_BOT_TOKEN]";

		SlackletService slackService = new SlackletService(botToken);
		slackService.start();

		String userName = "riversun";
		slackService.sendDirectMessageTo(userName, "Hello!");

		slackService.stop();

	}

}

```

### How to Run

1. Run **Example02**
2. Direct message will be send to user specified by user name.  

<img src="https://riversun.github.io/slacklet/img/ex02_00_direct_left.png"/>
<img src="https://riversun.github.io/slacklet/img/ex02_01_direct.png"/>

<hr>

##  4.Sending message to specified channel 

```java
public class Example03 {

	public static void main(String[] args) throws IOException {

		String botToken = "[YOUR_BOT_TOKEN]";

		SlackletService slackService = new SlackletService(botToken);
		slackService.start();

		String channelName = "random";
		slackService.sendMessageTo(channelName, "Hi to random!");

		slackService.stop();

	}

}

```

### How to Run

1. Run **Example03**  
2. Posted message will be send to channel specified by channel name.  

<img src="https://riversun.github.io/slacklet/img/ex03.png"/>

<hr>

## 5.Use session specific to the user
If you want to make **conversational bot** , the session will be useful for you.  
When interacting with the user, it is necessary to keep the context for each user.  
Then it establishes a conversation with the user.

```java
public class Example04 {

	public static void main(String[] args) throws IOException {

		Logger.setEnalbed(false);

		String botToken = "[YOUR_BOT_TOKEN]";

		SlackletService slackService = new SlackletService(botToken);

		slackService.addSlacklet(new Slacklet() {

			@Override
			public void onDirectMessagePosted(SlackletRequest req, SlackletResponse resp) {
				// BOT received direct message from user

				// get message content
				String content = req.getContent();

				// get session for this sender user.
				SlackletSession session = req.getSession();

				Integer num = (Integer) session.getAttribute("num", 1);

				resp.reply("No." + num + " You say '" + content + "'.");

				// count up
				num++;

				session.setAttribute("num", num);

			}

		});

		slackService.start();

	}

}
```


### How to Run

1. Run **Example04**  
2. Type like as follows.SlackletSession is unique context for each user like servlet session.  
You can store user specific in it.   
<img src="https://riversun.github.io/slacklet/img/ex04.png"/>  

# Maven

```xml
<dependency>
	<groupId>org.riversun</groupId>
	<artifactId>slacklet</artifactId>
	<version>1.0.2</version>
</dependency>

```
