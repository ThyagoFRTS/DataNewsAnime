package com.example.datanewsanime.handlexml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class HandleXML {

    public static List<NewsData> xmlExtract(String content){

        XmlPullParserFactory factory;
        XmlPullParser xmlParser;
        try {
            boolean itemTag = false;
            String currentTag = "";
            NewsData nd = null;
            List<NewsData> newsList = new ArrayList<>();


            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();

            xmlParser.setInput(new StringReader(content));

            int eventType = xmlParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        currentTag = xmlParser.getName();
                        if (currentTag.endsWith("item")){
                            itemTag = true;
                            nd = new NewsData();
                            newsList.add(nd);
                        }
                    break;
                    case XmlPullParser.END_TAG:
                        if (xmlParser.getName().equals("item")){
                            itemTag = false;
                        }
                        currentTag = "";
                    break;
                    case XmlPullParser.TEXT:
                        if (itemTag && nd != null){
                            switch (currentTag){
                                case "id":
                                    nd.setId(xmlParser.getText());
                                break;
                                case "type":
                                    nd.setType(xmlParser.getText());
                                    break;
                                case "name":
                                    nd.setName(xmlParser.getText());
                                break;
                                case "vintage":
                                    nd.setVintage(xmlParser.getText());
                                break;

                            }

                        }
                    break;
                }
                eventType = xmlParser.next();
            }
            return newsList;


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}
