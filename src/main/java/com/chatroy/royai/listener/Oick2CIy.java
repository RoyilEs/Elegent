package com.chatroy.royai.listener;



import com.chatroy.royai.utils.OK3HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.FilterValue;
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

   private String app_key = "35cece84b9177c32006439a30b95c57e";

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

   @Listener
   @Filter(value = "#对话 {{msg}}")
   public void ChatMsg(GroupMessageEvent event, @FilterValue("msg")String msg){
      MessagesBuilder messagesBuilder = new MessagesBuilder();
      String s = OK3HttpClient.httpGet("https://apis.tianapi.com/robot/index?key="+app_key+"&question=" + msg, null, null);
      JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);
      String asString = jsonObject.getAsJsonObject("result").get("reply").getAsString();
      messagesBuilder.text(asString);
      event.getGroup().sendBlocking(messagesBuilder.build());
      System.out.println(asString);
   }

   @Listener
   @Filter(value = "#合成 {{emoji1}}{{emoji2}}")
   public void Compost(GroupMessageEvent event, @FilterValue("emoji1")String emoji1, @FilterValue("emoji2")String emoji2) throws MalformedURLException {
      String s = OK3HttpClient.httpGet("https://api.eihei.site/API/emoji_synthesis.php?emoji_one="+emoji1+"&emoji_two="+emoji2, null, null);
      JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);
      String s1 = jsonObject.get("text").getAsString();
      Integer code = Integer.valueOf(jsonObject.get("code").getAsString());
      //获得合成表情
      String data = jsonObject.getAsJsonObject("url").get("url").getAsString();
      if (code == 1) {
         MessagesBuilder messagesBuilder = new MessagesBuilder()
                 .image(Resource.of(new URL(data)));
         event.getGroup().sendBlocking(messagesBuilder.build());
      } else if (code == -1) {
         event.getGroup().sendBlocking("球球大伙来点正常的emoji吧");
      } else {
         event.getGroup().sendBlocking("没有这种组合哦");
      }
      System.out.println(s1);
   }

}

