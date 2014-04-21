package com.javabot.javabot;

import java.util.ArrayList;
import java.util.List;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;

public class ParserRSS {

    private final String url;
    private final RssParser rss;

    public ParserRSS(String url) {
        this.url = url;
        rss = new RssParser();
    }

    public List<String> fetchRssFeed() {
        List<String> rssItems = new ArrayList<String>();
        try {
            RssFeed feed = rss.load(url);

            // Get feed items and generate the List
            List<RssItemBean> items = feed.getItems();
            for (int i = 0; i < items.size(); i++) {
                rssItems.add("Title: " + items.get(i).getTitle() + " - " + items.get(i).getLink());
            }
        } catch (Exception e) {
            rssItems.add("Error while executing fetchRssFeed! " + e);
            return rssItems;
        }
        return rssItems;
    }

}
