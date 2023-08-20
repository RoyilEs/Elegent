package com.chatroy.royai.listener;


import com.chatroy.royai.MsgOrImg.messageOrImg;
import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.FilterValue;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.*;
import love.forte.simbot.resources.PathResource;
import love.forte.simbot.resources.Resource;
import net.mamoe.mirai.event.events.NudgeEvent;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class HelloListener implements messageOrImg {
    Random random = new Random();
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

    String  tx = "腾讯是什么?tx是什么?";


//    @Listener
//    @Filter(value = "#查询歌曲 {{keyword}}")
//    public ResponseEntity<MessageReceipt> music(GroupMessageEvent event, @FilterValue("keyword")String keyword, MusicUrl musicUrl) {
//
//        //获得群昵称
//        String nickname = event.getAuthor().getNickname();
//        //获得ID
//        String id = String.valueOf(event.getAuthor().getId());
//
//        //获取Url
//        String url = "https://v2.alapi.cn/api/music/search?keyword="+keyword+"&token="+ musicUrl.getToken();
//
//        return ResponseEntity.ok(messageReceipt);
//    }


//    //猜数字游戏
//    @Listener
//    @Filter(value = "#随机猜数字({{num}})")
//    public void randomGame(GroupMessageEvent event, @FilterValue("num")String num) {
//
//        Integer num2 = random.nextInt(Integer.parseInt(num)) + 1;
//
//        for (int i = 0; i < 10; i++) {
//            while (true)
//            {
//
//            }
//        }
//    }



    @Listener
    @Filter(value = "/help")
    public void help(GroupMessageEvent event){
        event.getSource().sendBlocking("如果无聊了可以@Royill 你好(会有意想不到的惊喜)\ntest功能\n#求签功能" +
                "\n#来点AI图片(输入数字指定图片)"
                 );
//        "\n需要定制功能请联系Roy(QQ:2839182980)"
    }

    @Listener
    @Filter(value = "#来点AI图片")
    public void img(GroupMessageEvent event){
        int i = random.nextInt(IMG_AI.length);
        MessagesBuilder image = new MessagesBuilder().image(IMG_AI[i]);
        event.getSource().sendBlocking(image.build());
    }



    @Listener
    @Filter(value = "{{str}}",targets = @Filter.Targets(atBot = true))
    @ContentTrim // 当匹配被at时，将'at'这个特殊消息移除后，剩余的文本消息大概率存在前后空格，通过此注解在匹配的时候忽略前后空格
    public void onChannelMessage(GroupMessageEvent event , @FilterValue("str")String str, NudgeEvent nudgeEvent) {// 将要监听的事件类型放在参数里，即代表监听此类型的消息

        if (str.equals("你好")) {
            int i = random.nextInt(2);

            if (i == 1) {
                PathResource resource = Resource.of(Path.of("img/2.png"));
                Messages append = new MessagesBuilder()
                        .text("请问有什么事么^^")
                        .image(resource)
                        .build();
                event.replyBlocking(append);// Java中的阻塞式API
            } else {
                MessagesBuilder image = new MessagesBuilder().image(IMG[1]);
                event.getGroup().sendBlocking(image.build());
            }
        }

        if (tx.contains(str)) {
            event.getGroup().sendBlocking("是那个只喜欢钱,游戏里面都是氪金元素,注册的账号不是自己的账号的投资公司吗");
        }

        if(str.equals("学习资料")) {
            Messages elements = new MessagesBuilder()
                    .text("java基础(尚硅谷):" + "https://www.bilibili.com/video/BV1PY411e7J6" + "\n")
                    .text("Go基础:" + "https://www.bilibili.com/video/BV1Pg41187AS\n")
                    .text("simbot文档:" + "https://simbot.forte.love/\n")
                    .text("mirai文档:" + "https://docs.mirai.mamoe.net/\n")
                    .text("springboot官方文档:"+"https://spring.io/projects/spring-cloud-aws\n")
                    .text("LeetCode官网"+"https://leetcode.cn/problemset/all/\n")
                    .text("Vue文档:"+"https://cn.vuejs.org/")
                    .build();
            event.getGroup().sendBlocking(elements);
        }
    }

    @Listener
    public  void need(GroupMessageEvent event){
        //获得信息
        String plainText = event.getMessageContent().getPlainText();
        //获得用户
        String username = event.getAuthor().getNickOrUsername();
        if (plainText.equals("#求签")){
            event.getSource().sendBlocking("你在祈求什么呢？");
        } else if (plainText.startsWith("#求签")){
            String[] msg = plainText.split(" ");
            String msg1 = msg[1];
            //获取当前时间戳
            Date date = new Date(System.currentTimeMillis());
            int i = random.nextInt(MSG.length);
            int i_img = random.nextInt(IMG_AI.length);
            Messages elements = new MessagesBuilder()
                    .text("今天是" + format.format(date)+"\n"
                            +username+"所求的:"+"\""+msg1+"\"\n\n" +
                            "气运:"+"【"+MSG[i]+"】")
                    .image(IMG_AI[i_img])
                    .build();
            event.getGroup().sendBlocking(elements);
        }
    }

//    @Listener
//    public void test1(MiraiGroupNudgeEvent nudgeEvent) {
//        nudgeEvent.getGroup().sendBlocking("114514");
//    }

}

