package com.chatroy.royai.listener;

import com.chatroy.royai.utils.OK3HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.Messages;
import love.forte.simbot.message.MessagesBuilder;
import love.forte.simbot.resources.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

@Component
public class DDTool {
    Random random = new Random();
    private String DD_Api = "https://cfapi.vtbs.moe";

    @Listener
    public void DDTool_Task(GroupMessageEvent event) throws MalformedURLException {
        //获得信息
        String plainText = event.getMessageContent().getPlainText();
        switch (plainText) {
            case "#今天看谁" :
            case "#今天d谁"  :
                String res = OK3HttpClient.httpGet(DD_Api + "/v1/vtbs", null, null);
                JsonArray jsonArray = new Gson().fromJson(res, JsonArray.class);
                JsonArray vtbs = jsonArray.getAsJsonArray();
                int index = random.nextInt(vtbs.size());
                //第一次拆解
                String vtbId = vtbs.get(index).getAsJsonObject().get("mid").getAsString();
                //第二次拆解
                String RandVtb = OK3HttpClient.httpGet(DD_Api + "/v1/detail/" + vtbId,  null, null);
                JsonObject RandVtbJsonObject = new Gson().fromJson(RandVtb, JsonObject.class);
                String uname = RandVtbJsonObject.get("uname").getAsString();
                //直播间ID
                String roomid = RandVtbJsonObject.get("roomid").getAsString();
                //头像
                String face = RandVtbJsonObject.get("face").getAsString();
                //粉丝数
                String followers = RandVtbJsonObject.get("follower").getAsString();
                //在线状态
                Integer online = Integer.valueOf(RandVtbJsonObject.get("online").getAsString());
                //签名
                String sign = RandVtbJsonObject.get("sign").getAsString();
                //直播间标题
                String title = RandVtbJsonObject.get("title").getAsString();

                if(online == 0) {
                    Messages msg = new MessagesBuilder()
                            .image(Resource.of(new URL(face)))
                            .text("名称:" + uname + "\n" +
                                    "粉丝数:" + followers + "\n" +
                                    "签名:" + sign + "\n" +
                                    "主页:" + "https://space.bilibili.com/" + vtbId + "\n" +
                                    "直播间:" + "https://live.bilibili.com/" + roomid + "\n"
                            )
                            .build();
                    event.getSource().sendBlocking(msg);
                } else {
                    Messages msg =  new MessagesBuilder()
                            .image(Resource.of(new URL(face)))
                            .text(
                                    "名称:" + uname + "\n" +
                                    "粉丝数:" + followers + "\n" +
                                    "签名:" + sign + "\n" +
                                    "主页:" + "https://space.bilibili.com/" + vtbId + "\n" +
                                    "正在直播中! \n" +
                                    "直播间标题:" + title + "\n" +
                                    "直播间:" + "https://live.bilibili.com/" + roomid + "\n"

                            ).build();
                    event.getGroup().sendBlocking(msg);
                } break;
            case "#现在看谁" :
            case "#现在d谁"  :
                String res_now = OK3HttpClient.httpGet(DD_Api + "/v1/living", null, null);
                JsonArray resJsonArray_now = new Gson().fromJson(res_now, JsonArray.class);
                JsonArray vtbs_now = resJsonArray_now.getAsJsonArray();
                String RandVtbID = vtbs_now.get(random.nextInt(vtbs_now.size())).getAsString();
                String RoomInfo = OK3HttpClient.httpGet(DD_Api + "/v1/room/" + RandVtbID, null, null);
                JsonObject resjsonObject_now = new Gson().fromJson(RoomInfo, JsonObject.class);
                String uID = resjsonObject_now.get("uid").getAsString();
                String popularity = resjsonObject_now.get("popularity").getAsString();

                String RandVtb_now = OK3HttpClient.httpGet(DD_Api + "/v1/detail/" + uID, null, null);
                JsonObject RandVtbJsonObject_now = new Gson().fromJson(RandVtb_now, JsonObject.class);
                String uname_now = RandVtbJsonObject_now.get("uname").getAsString();
                //直播间ID
                String roomid_now = RandVtbJsonObject_now.get("roomid").getAsString();
                //头像
                String face_now = RandVtbJsonObject_now.get("face").getAsString();
                //粉丝数
                String followers_now = RandVtbJsonObject_now.get("follower").getAsString();
                //签名
                String sign_now = RandVtbJsonObject_now.get("sign").getAsString();
                //直播间标题
                String title_now = RandVtbJsonObject_now.get("title").getAsString();
                Messages msg_vtb_now = new MessagesBuilder()
                        .image(Resource.of(new URL(face_now)))
                        .text("标题:" + title_now + "\n"
                                + "人气值:" + popularity + "\n"
                                + "直播间:" +"https://live.bilibili.com/"+ roomid_now + "\n"
                                + "vtb信息 \n"
                                + "主播:" + uname_now
                                + "粉丝数:" + followers_now + "\n"
                                + "签名:" + sign_now + "\n"
                                + "主页:" + "https://space.bilibili.com/" + uID
                        ).build();
                event.getGroup().sendBlocking(msg_vtb_now);
        }
    }
}
