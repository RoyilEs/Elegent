package com.chatroy.royai.listener;

import com.chatroy.royai.MsgOrImg.User;
import com.chatroy.royai.MsgOrImg.messageOrImg;
import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.FilterValue;
import love.forte.simboot.annotation.Listener;
import love.forte.simboot.filter.MatchType;
import love.forte.simbot.definition.Member;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.MessagesBuilder;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
public class MasterListener implements messageOrImg , User {

    Random random = new Random();
    String APXS = "APXSAPaplrhLRHapxs";
    String RTWYZZ = "rtwyzzlilei李磊小菲柱";

    String ROY = "RoyRoyzxd张贤德";
    @Listener
    public void Test(GroupMessageEvent event) {
        String plainText = event.getMessageContent().getPlainText();

        int i = random.nextInt(testMsg.length);
        switch (plainText){
            case "test" -> event.getSource().sendBlocking(testMsg[i]);
            case "查询bot状态" -> event.getSource().sendBlocking(testMsg[i]);
            case "テスト" -> event.getSource().sendBlocking(testMsg[i]);
            case "测试" -> event.getSource().sendBlocking(testMsg[i]);
            case "114" -> event.getGroup().sendBlocking("514");
        }
    }

    @Listener
    @Filter(value = "叫主人")
    public void Roy(GroupMessageEvent event){
//        获得发送的成员信息
        Member author = event.getAuthor();
        System.out.println(author);
        String username = event.getAuthor().getUsername();
        String id = String.valueOf(event.getAuthor().getId());
        System.out.println(id);
        if (id.equals("2839182980")) {
            event.getGroup().sendBlocking(username + "主人最喜欢你了❤");
        } else {
            event.getGroup().sendBlocking("你不是我的主人,就不叫就不叫");
        }
    }

    @Listener
    @ContentTrim
    @Filter(value = "#来点AI图片 {{language}}", matchType = MatchType.REGEX_CONTAINS)
    public void enToCn(GroupMessageEvent messageEvent, @FilterValue("language")String language){
        Integer num =  Integer.valueOf(language);
        if (num <= IMG_AI.length) {
            MessagesBuilder image = new MessagesBuilder()
                    .image(IMG_AI[num-1]);
            messageEvent.getGroup().sendBlocking(image.build());
        } else {
            messageEvent.getGroup().sendBlocking("越界啦,根本没有这么多图片啊歪\n真的是个罕见呢");
        }
    }


    @Listener
    @ContentTrim
    @Filter(value = "来点{{name}}")
    public void getNameMsg(GroupMessageEvent messageEvent, @FilterValue("name")String name) {
        //APXS
        if (APXS.contains(name)) {
            int i = random.nextInt(3);
            if (i == 1) {
                int ap1 = random.nextInt(Ap.length);
                messageEvent.getGroup().sendBlocking(Ap[ap1]);
            } else {
                int ap2 = random.nextInt(ap_IMG.length);
                MessagesBuilder builder = new MessagesBuilder()
                        .image(ap_IMG[ap2]);
                messageEvent.getGroup().sendBlocking(builder.build());
            }
        }
        //Roy
        if (name.matches("Roy") ||name.matches("zxd")) {
                int Roy1 = random.nextInt(Roy.length);
                messageEvent.getGroup().sendBlocking(Roy[Roy1]);
        }
        //rtwyzz 李磊
        if (RTWYZZ.contains(name)) {
            int rtwyzz1 = random.nextInt(Rtwyzz.length);
            messageEvent.getGroup().sendBlocking(Rtwyzz[rtwyzz1]);
        }
    }

    @Listener
    @ContentTrim
    @Filter(value = "all 来点{{name}}")
    public void getAllNameMsg(GroupMessageEvent messageEvent, @FilterValue("name")String name) {
        if (APXS.contains(name)) {
            String apMsg = Arrays.toString(Ap);
            messageEvent.getGroup().sendBlocking(apMsg);
        }
        if (RTWYZZ.contains(name)) {
            String apMsg = Arrays.toString(Rtwyzz);
            messageEvent.getGroup().sendBlocking(apMsg);
        }
        if (ROY.contains(name)) {
            String apMsg = Arrays.toString(Roy);
            messageEvent.getGroup().sendBlocking(apMsg);
        }
    }


}
