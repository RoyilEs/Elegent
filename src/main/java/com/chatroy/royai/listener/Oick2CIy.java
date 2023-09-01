package com.chatroy.royai.listener;


import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;


import love.forte.simbot.message.MessagesBuilder;

import love.forte.simbot.resources.Resource;

import org.springframework.stereotype.Component;

import java.io.IOException;

import java.net.URL;


@Component
public class Oick2CIy {

    @Listener
    @Filter(value = "#来点二次元")
    public void Img2(GroupMessageEvent event) throws IOException {

        MessagesBuilder messagesBuilder = new MessagesBuilder();
        messagesBuilder.image(Resource.of(new URL("https://api.oick.cn/random/api.php?type=pc")));
        event.getGroup().sendBlocking(messagesBuilder.build());

    }
}

