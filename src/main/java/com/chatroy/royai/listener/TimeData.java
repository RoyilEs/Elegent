package com.chatroy.royai.listener;

import com.google.gson.Gson;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class TimeData {
    private String time;
    private Long milliseconds_since_epoch;
    private String date;

    @Override
    public String toString() {

        return "TimeData\n" + "当前时间:" + time +  "\ndate:" + date;
    }

    @Component
    public class GsonReadWebPage {
        @Listener
        @Filter(value = "报时")
        public void main(GroupMessageEvent event) throws IOException {

            String webPage = "http://time.jsontest.com";

            try (InputStream is = new URL(webPage).openStream();
                 Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {

                Gson gson = new Gson();
                TimeData td = gson.fromJson(reader, TimeData.class);
               event.getGroup().sendBlocking(String.valueOf(td));

            }
        }
    }
}
