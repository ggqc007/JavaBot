package com.javabot.javabot;

import java.util.List;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class CmdRSS extends ListenerAdapter {

    private static final String SLASHDOT_URL = "http://rss.slashdot.org/Slashdot/slashdot";
    private static final String SLASHDOT_TRIGGER = ".slashdot";

    private static final String SECURITY_FOCUS = "http://www.securityfocus.com/rss/vulnerabilities.xml";
    private static final String SECURITY_FOCUS_TRIGGER = ".security";

    private static final int ITEMS_SHOWN = 5;

    @Override
    public void onMessage(MessageEvent event) {
        if (event.getMessage().equalsIgnoreCase(SECURITY_FOCUS_TRIGGER)) {
            rssToIrcChannel(event, SECURITY_FOCUS);
        } else if (event.getMessage().equalsIgnoreCase(SLASHDOT_TRIGGER)) {
            rssToIrcChannel(event, SLASHDOT_URL);
        }
    }

    private void rssToIrcChannel(MessageEvent event, String rssUrl) {
        List<String> siteFeed;

        siteFeed = generateFeedList(rssUrl);
        for (int i = 0; i < siteFeed.size() && i < ITEMS_SHOWN; i++) {
            event.respond(siteFeed.get(i));
        }
    }

    private List<String> generateFeedList(String rssUrl) {
        ParserRSS rssObj = new ParserRSS(rssUrl);
        return rssObj.fetchRssFeed();
    }
}
