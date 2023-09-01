package com.chatroy.royai.listener;



import com.chatroy.royai.utils.OK3HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.MessagesBuilder;
import love.forte.simbot.resources.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;


@Component
public class Oick2CIy {
   private String name;
   private String url;
   private String picurl;

   String[] sort = {"热歌榜","新歌榜","飙升榜","抖音榜","电音榜"};
   Random random = new Random();

   @Listener
   @Filter(value = "#随机歌曲")
   public void RandMusic(GroupMessageEvent event) throws MalformedURLException {
      MessagesBuilder messagesBuilder = new MessagesBuilder();
      int i = random.nextInt(sort.length);
      String s = OK3HttpClient.httpGet("https://api.uomg.com/api/rand.music?sort=" + sort[i] + "&format=json", null, null);
      JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);
      JsonObject data = jsonObject.getAsJsonObject("data");
      String name = data.get("name").getAsString();
      String url = data.get("url").getAsString();
      String picurl = data.get("picurl").getAsString();
      messagesBuilder.append("歌曲名字:"+name+"\n");
      messagesBuilder.append("听歌地址:"+url+"\n");
      messagesBuilder.image(Resource.of(new URL(picurl)));
      event.getGroup().sendBlocking(messagesBuilder.build());
   }
}

