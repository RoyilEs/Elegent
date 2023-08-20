package com.chatroy.royai;


import love.forte.simboot.spring.autoconfigure.EnableSimbot;

import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import xyz.cssxsh.mirai.tool.FixProtocolVersion;



@EnableSimbot
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RoyAiApplication {
    public static void main(String... args) {

        FixProtocolVersion.fetch(BotConfiguration.MiraiProtocol.ANDROID_PHONE, "8.9.63");
        SpringApplication.run(RoyAiApplication.class, args);

        System.out.println("Hello World");

    }


}
