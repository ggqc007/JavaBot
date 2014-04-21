package com.javabot.javabot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.managers.ListenerManager;
import org.pircbotx.hooks.managers.ThreadedListenerManager;

public class JavaBot {

    private static final String BOT_NAME = "JavaBot";
    private static final String BOT_IDENT = "version1";
    private static final String IRCD_ADDRESS = "irc.amaama.org";
    private static final String IRCD_CHANNEL[] = {"#OldSchool"};

    private static Configuration botConfig;
    private static final ListenerManager manager = new ThreadedListenerManager();

    private static Configuration buildBotConfig() {
        botConfig = new Configuration.Builder()
                .setName(BOT_NAME)
                .setLogin(BOT_IDENT)
                .setAutoNickChange(true)
                .setCapEnabled(true)
                .setServerHostname(IRCD_ADDRESS)
                .addAutoJoinChannel(IRCD_CHANNEL[0])
                .setListenerManager(manager)
                .buildConfiguration();
        return botConfig;
    }

    private static void addEventsListeners() {
        manager.addListener(new CmdRSS());
    }

    public static void main(String[] args) throws Exception {
        botConfig = buildBotConfig();
        addEventsListeners();

        PircBotX bot = new PircBotX(botConfig);
        bot.startBot();

    }
}
